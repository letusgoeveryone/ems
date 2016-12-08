/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.dao.TeacherDao;
import com.letusgo.model.Teacher;
import java.util.Collection;
import org.hibernate.Query;
import java.util.LinkedList;
/**
 *
 * @author Administrator
 */
public class TeacherDaoImp extends DaoImpl<Teacher> implements TeacherDao{
    @Override
    public void addTeacher(Teacher teacher) {
        save(teacher);
    }

    @Override
    public void addAllTeacher(Collection<Teacher> teachers){
        for (Teacher teacher : teachers) {
            save(teacher);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        if(teacher.getId() == null){
            teacher = getTeacherBySn(teacher.getSn());
        }
        getSession().update(teacher);
    }

    @Override
    public void deleteTeacher(Teacher dTeacher) {
        if(dTeacher.getId() == null){
            dTeacher = getTeacherBySn(dTeacher.getSn());
        }
        delete(dTeacher);
    }

    @Override
    public void deleteAllTeacher(Collection<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            delete(teacher);
        }
    }

    @Override
    public void deleteTeacherBySn(String teaSn) {
        Query query = getSession().getNamedQuery("Teacher.findBySn");
        query.setString("sn", teaSn);
        Teacher teacher = (Teacher) query.uniqueResult();
        getSession().delete(teacher);
    }

    @Override
    public void deleteAllTeacherBySn(Collection<String> teaSns) {
        for (String teaSn : teaSns) {
            deleteTeacherBySn(teaSn);
        }
    }

    @Override
    public Teacher getTeacherBySn(String teaSn) {
        Query query = getSession().getNamedQuery("Teacher.findBySn");
        query.setString("sn", teaSn);
        return (Teacher) query.uniqueResult();
    }

    @Override
    public Collection<Teacher> getAllTeacherBySn(Collection<String> teaSns) {
        LinkedList<Teacher> linkedList = new LinkedList<>();
        for (String teacher : teaSns) {
            linkedList.add(getTeacherBySn(teacher));
        }
        return linkedList;
    }


}
