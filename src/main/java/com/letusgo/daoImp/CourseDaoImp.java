/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;

import com.letusgo.dao.CourseDao;
import com.letusgo.model.Course;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
public class CourseDaoImp extends DaoImpl<Course> implements CourseDao{

    @Override
    public void addCourse(Course course) {
        getSession().save(course);
    }

    @Override
    public void addAllCourse(Collection<Course> courses) {
        courses.stream().forEach((course) -> {
            save(course);
        });
    }

    @Override
    public void deleteCourse(Course course) {
        if (course.getId() == null) {
            Query namedQuery = getSession().getNamedQuery("Course.findByName");
            namedQuery.setString("name", course.getName());
            course = (Course) namedQuery.uniqueResult();
        }
        getSession().delete(course);
    }

    @Override
    public void deleteAllCourse(Collection<Course> courses) {
        for (Course course : courses) {
            getSession().delete(course);
        }
    }

    @Override
    public void updateCourse(Course course) {
        if (course.getId() == null) {
            Course coursesByName = getCoursesByName(course.getName());
            getSession().evict(coursesByName);
            course.setId(course.getId());
        }
        getSession().update(course);
    }

    @Override
    public void updateAllCourse(Collection<Course> courses) {
        for (Course course : courses) {
            updateCourse(course);
        }
    }

    @Override
    public Collection<Course> getAllCourses() {
        return getSession().getNamedQuery("Course.findAll").list();
    }

    @Override
    public Course getCoursesByName(String courseName) {
        return (Course) getSession().getNamedQuery("Course.findByName").setString("name", courseName).uniqueResult();
    }

    @Override
    public Collection<Course> getCoursesByCollege(String collegeName) {
        String hql = "FROM Course c WHERE c.college.name = :name";
        Map<String,Object> map = new HashMap();
        map.put("name", collegeName);
        Query query = getQuery(hql, map);
        return query.list();
    }

    @Override
    public Course getCoursesByNumber(String collegeNumber) {
        Query namedQuery = getSession().getNamedQuery("Course.findByNumber");
        namedQuery.setString("number", collegeNumber);
        
        return (Course) namedQuery.uniqueResult();
    }
    
    
    
}
