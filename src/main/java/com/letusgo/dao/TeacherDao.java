/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;


import com.letusgo.model.Teacher;
import java.util.Collection;

/**
 *老师相关的一些操作.<br>
 * 
 * 
 * @author 王鸿运
 * @version 1.0
 */
public interface TeacherDao {
    /**
     *老师注册.<br>
     * 将一个老师注册到数据库
     * @param teacher 老师对象 
     * 
     */
    public void addTeacher(Teacher teacher);
    
    /**
     *老师注册.<br>
     * 将多个老师注册到数据库，加入了批处理，多个处理速度更快
     * @param teachers 老师对象集合
     * 
     */
    public void addAllTeacher(Collection<Teacher> teachers);
    
    
    /**
     *更新单个老师信息.<br>
     * 更新单个老师的信息
     * 更新一个老师对象可以通过两个值确定数据库对应的列  id或者sn
     * 有一个是正确的就可以正常更新，若都没有，可能会报空指针
     * @param teacher dao层的dteacher对象 
     * 
     */
    public void updateTeacher(Teacher teacher);
    
    /**
     *注销单个老师.<br>
     * 将单个老师从数据库中
     * @param dTeacher dto层的dteacher对象 
     * 
     * 
     */
    public void deleteTeacher(Teacher dTeacher);
    
    /**
     *注销多个老师.<br>
     * 将多个老师从数据库注销，加入了批处理，多个处理速度更快
     * @param teachers dto层老师对象dteacher的集合
     * 
     */
    public void deleteAllTeacher(Collection<Teacher> teachers);
    
    
    /**
     *根据学号注销老师.<br>
     * 根据学号将一个老师从数据库注销
     * 
     * @param teaSn 老师学号
     */
    public void deleteTeacherBySn(String teaSn);
    
    /**
     *根据学号注销多个老师.<br>
     * 将多个老师从数据库注销
     * @param teaSns 老师学号的集合
     */
    public void deleteAllTeacherBySn(Collection<String> teaSns);
    
    /**
     *根据学号查询老师.<br>
     * 根据学号查询单个老师
     * 
     * @param teaSn 老师学号
     * @return 返回单个老师对象
     */
    public Teacher getTeacherBySn(String teaSn);
    
    /**
     *根据学号集合查询多个老师.<br>
     * 根据学号集合查询多个老师
     * @param teaSns 老师学号的集合
     * @return 返回老师对象的集合
     */
    public Collection<Teacher> getAllTeacherBySn(Collection<String> teaSns);
     
    
    /**
     *设置学期课程<br>
     * 如果根据参数可以查到已有对应的学期课程，则不作为
     * @param teacherId 教师id
     * @param courseId 课程id
     * @param term 学期
     * @
     */
    public void setTermCourse(int teacherId,int courseId,String term);
    
    /**
     *设置学期课程<br>
     * 如果根据参数可以查到已有对应的学期课程，则不作为
     * @param teacherId 教师id
     * @param courseId 课程id
     * @param term 学期
     * @
     */
    public void setTermCourse(int courseId,String term);
}
