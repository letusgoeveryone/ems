/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.dao.KeyValueDao;
import com.letusgo.model.Keyvalue;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
public class KeyValueDaoImp extends DaoImpl<Keyvalue> implements KeyValueDao{

    @Override
    public void save(String keyString, String valueString) {
        Keyvalue valueByKey = getValueByKey(keyString);
        if (valueByKey == null) {
            getSession().save(new Keyvalue(keyString, valueString));
        }else{
            valueByKey.setIvalue(valueString);
            getSession().update(valueByKey);
        }
        
        
    }

    @Override
    public void delete(String keyString) {
        getSession().delete(getValueByKey(keyString));
    }

    @Override
    public Keyvalue getValueByKey(String keyString) {
        Query query = getSession().getNamedQuery("Keyvalue.findByIkey");
        query.setString("ikey", keyString);
        Keyvalue keyvalue = (Keyvalue) query.uniqueResult();
        
        return keyvalue;
        
    }
    
}
