/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 * @param <T>
 */
public interface IDao<T> {
    public T find(Class<T> clazz, int id);

    public void create(T t);

    public void save(T t);

    public void delete(T t);

    /**
     * 查询某页实体
     * @param hql 查询语句
     * @param firstResult 从第几条开始，注意索引从0开始
     * @param maxResults 最多返回的数据条数
     * @param map 参数键值对
     * @return
     */
    public List<T> list(String hql, int firstResult, int maxResults,
            Map<String, Object> map);

    public Query getQuery(String hql, Map<String, Object> map);

    /**
     * 查询实体
     * @param hql
     * @param map
     * @return
     */
    List<T> list(String hql, Map<String, Object> map);

    /**
     * 获取记录总数
     * @param hql
     * @param map
     * @return
     */
    int getTotalCount(String hql, Map<String, Object> map);
}
