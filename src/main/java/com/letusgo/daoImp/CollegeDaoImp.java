/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.model.College;
import java.util.Collection;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
public class CollegeDaoImp extends DaoImpl<College> implements com.letusgo.dao.CollegeDao{

    @Override
    public void addCollege(College college) {
        
        getSession().save(college);
    }

    @Override
    public void deleteCollege(College college) {
        
        if (college.getId() == null) {
            getCollegeByName(college.getName());
        }
        getSession().delete(college);
    }

    @Override
    public void delteCollegeByName(String name) {
        
        getSession().delete(getCollegeByName(name));
    }

    @Override
    public void updateCollege(College college) {
        
        if (college.getId() == null) {
            College preCollege = getCollegeByName(college.getName());
            getSession().evict(preCollege);
            college.setId(preCollege.getId());
        }
        getSession().update(college);
        
    }

    @Override
    public void updateCollegeByName(String name) {
        
        College   college = getCollegeByName(name);
        getSession().update(college);
    }

    @Override
    public College getCollegeByName(String name) {
        
        Query namedQuery = getSession().getNamedQuery("College.findByName");
        getSession().createSQLQuery(name);
        return (College) namedQuery.uniqueResult();
    }

    @Override
    public Collection<College> getAllCollege() {
        
        return getSession().getNamedQuery("College.findAll").list();
    }
    
}
