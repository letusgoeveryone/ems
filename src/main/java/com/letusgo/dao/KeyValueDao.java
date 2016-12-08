/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;

import com.letusgo.model.Keyvalue;

/**
 *
 * @author Administrator
 */
public interface KeyValueDao {
    public void save(String keyString,String valueString);
    public void delete(String keyString);
    public Keyvalue getValueByKey(String keyString);
    
    
}
