/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;

import com.letusgo.model.Course;
import java.util.Collection;

/**
 *
 * @author Administrator
 */
public interface CourseDao {
    
    /**
     *添加课程
     * @param course
     */
    public void addCourse(Course course);
    
    /**
     *通过集合添加课程
     * @param courses
     */
    public void addAllCourse(Collection<Course> courses);
    
    
    /**
     *删除课程
     * @param course
     */
    public void deleteCourse(Course course);
    
    /**
     *通过集合删除课程
     * @param courses
     */
    public void deleteAllCourse(Collection<Course> courses);
    
    /**
     *更新课程
     * 根据id或name更新
     * 都没有会报空指针
     * @param course
     */
    public void updateCourse(Course course);
    
    /**
     *通过集合更新课程
     * @param courses
     */
    public void updateAllCourse(Collection<Course> courses);
    
    /**
     *获取所有课程对象
     * @return 课程对象的集合
     */
    public Collection<Course> getAllCourses();
    
    /**
     *根据学院名返回课程列表
     * @param collegeNumber 学院编号
     * @return 课程对象
     */
    public Course getCoursesByNumber(String collegeNumber);
    
    /**
     *根据课程名返回课程对象
     * @param courseName 课程名
     * @return 课程对象
     */
    public Course getCoursesByName(String courseName);
    
    
    /**
     *根据学院名返回课程列表
     * @param collegeName 学院名
     * @return 课程对象
     */
    public Collection<Course> getCoursesByCollege(String collegeName);
    
    
    
}
