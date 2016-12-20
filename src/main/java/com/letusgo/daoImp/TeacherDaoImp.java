/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.dao.TeacherDao;
import com.letusgo.model.Course;
import com.letusgo.model.Teacher;
import com.letusgo.model.Termcourse;

import java.util.Collection;
import org.hibernate.Query;
import java.util.LinkedList;
import java.util.List;
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

    public void setTermCourse(int teacherId,int CourseId,String term){
    	Termcourse tempTermcourse = (Termcourse) getSession()
    	.createQuery("FROM Termcourse t where t.term = :term and t.course.id = :cid")
    	.setString("term",term).setInteger("cid", CourseId).uniqueResult();
    	if (tempTermcourse == null) {
    		Teacher teacher = getSession().get(Teacher.class, teacherId);
        	Course course = getSession().get(Course.class, CourseId);
        	Termcourse termcourse = new Termcourse(term, course, teacher);
			getSession().saveOrUpdate(termcourse);
		}else{
			tempTermcourse.setTeacher(getSession().get(Teacher.class, teacherId));
		}
    }
    
    public void setTermCourse(int CourseId,String term){
    	Termcourse tempTermcourse = (Termcourse) getSession()
    	.createQuery("FROM Termcourse t where t.term = :term and t.course.id = :cid")
    	.setString("term",term).setInteger("cid", CourseId).uniqueResult();
    	if (tempTermcourse == null) {
    		
        	Course course = getSession().get(Course.class, CourseId);
        	Termcourse termcourse = new Termcourse(term, course, null);
			getSession().saveOrUpdate(termcourse);
		}
    	
    	
    }
}
