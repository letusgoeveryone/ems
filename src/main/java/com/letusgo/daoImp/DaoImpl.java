/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.dao.IDao;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * 
 * @author 王鸿运
 * @param <T>
 */
public class DaoImpl<T> implements IDao<T>{
    private SessionFactory sessionFactory;

    @Override
    public T find(Class<T> clazz, int id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public void create(T t) {
        getSession().persist(t);
    }

    @Override
    public void save(T t) {
        getSession().saveOrUpdate(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public List<T> list(String hql, int firstResult, int maxResults, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        List<T> list = query.setFirstResult(firstResult)
                .setMaxResults(maxResults).list();
        System.out.println("起始:"+firstResult);
        System.out.println("最大值:"+maxResults);
        System.out.println("实际值:"+list.size());

        return list;
    }

    @Override
    public Query getQuery(String hql, Map<String, Object> map) {
        Query query = getSession().createQuery(hql);
        // TODO Auto-generated method stub
        if (map != null) {  
            Set<String> keySet = map.keySet();  
            for (String string : keySet) {  
                Object obj = map.get(string);  
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(obj instanceof Collection<?>){  
                    query.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                    query.setParameterList(string, (Object[])obj);  
                }else{  
                    query.setParameter(string, obj);  
                }  
            }  
        }  
        return query;  
    }

    @Override
    public List<T> list(String hql, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        List<T> list = query.list();
        return list;
    }

    @Override
    public int getTotalCount(String hql, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        System.out.println("hql"+hql);
        Object obj = query.uniqueResult();
        return ((Long) obj).intValue();
    }
    
    
     public Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
