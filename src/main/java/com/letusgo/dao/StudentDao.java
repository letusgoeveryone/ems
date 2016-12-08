/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.dao;



import java.util.Collection;
import com.letusgo.model.Student;
import com.letusgo.model.Selectcourse;

/**
 *操作学生数据.<br>
 * 关于学生的操作
 * 
 * @author 王鸿运
 * @version 1.0
 * 
 * 
 */
public interface StudentDao {
    
    /**
     *学生注册.<br>
     * 将一个学生注册到数据库
     * @param student 学生对象 
     * 
     */
    public void addStudent(Student student);
    
    /**
     *学生注册.<br>
     * 将多个学生注册到数据库，加入了批处理，多个处理速度更快
     * @param students 学生对象集合
     * 
     */
    public void addAllStudent(Collection<Student> students);
    
    
    /**
     *更新单个学生信息.<br>
     * 更新一个学生对象可以通过两个值确定数据库对应的列  id或者sn
     * 有一个是正确的就可以正常更新，若都没有，可能会报空指针
     * @param student dto层的dstudent对象 
     * 
     */
    public void updateStudent(Student student);
    
    /**
     *注销单个学生.<br>
     * 将单个学生从数据库中
     * @param dStudent dto层的dstudent对象 
     * 
     * 
     */
    public void deleteStudent(Student dStudent);
    
    /**
     *注销多个学生.<br>
     * 将多个学生从数据库注销，加入了批处理，多个处理速度更快
     * @param students dto层学生对象dstudent的集合
     * 
     */
    public void deleteAllStudent(Collection<Student> students);
    
    
    /**
     *根据学号注销学生.<br>
     * 根据学号将一个学生从数据库注销
     * 
     * @param stuSn 学生学号
     */
    public void deleteStudentBySn(String stuSn);
    
    /**
     *根据学号注销多个学生.<br>
     * 将多个学生从数据库注销
     * @param stuSns 学生学号的集合
     */
    public void deleteAllStudentBySn(Collection<String> stuSns);
    
    /**
     *根据学号查询学生.<br>
     * 根据学号查询单个学生
     * 
     * @param stuSn 学生学号
     * @return 返回单个学生对象
     */
    public Student getStudentBySn(String stuSn);
    
    /**
     *根据学号集合查询多个学生.<br>
     * 根据学号集合查询多个学生
     * @param stuSns 学生学号的集合
     * @return 返回学生对象的集合
     */
    public Collection<Student> getAllStudentBySn(Collection<String> stuSns);
    
    /**
     *为学生选课
     * 
     * @param student 学生对象
     * @param selectcourse 选课
     * 
     */
    public void selectCourse(Student student,Selectcourse selectcourse);
    
    
    
    
    

    
}
