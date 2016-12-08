/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;

import com.letusgo.model.College;
import java.util.Collection;

/**
 *
 * @author Administrator
 */
public interface CollegeDao {
    
    /**
     *添加学院
     */
    public void addCollege(College college);
    
    /**
     *删除学院
     * @param college
     */
    public void deleteCollege(College college);
    
    /**
     *根据学院名删除学院
     * @param name
     */
    public void delteCollegeByName(String name);
    
    /**
     *更新学院
     * @param college
     */
    public void updateCollege(College college);
    
    /**
     *根据学院名更新学院
     * @param name
     */
    public void updateCollegeByName(String name);
    
    /**
     *根据学院名获取学院
     * @param name
     * @return
     */
    public College getCollegeByName(String name);
    
    /**
     *获取所有学院
     * @return
     */
    public Collection<College> getAllCollege();
    
    
    
    
    
}
