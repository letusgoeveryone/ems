/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.daoImp;


import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.dao.StudentDao;
import com.letusgo.model.Selectcourse;
import com.letusgo.model.Student;
import java.util.Collection;
import java.util.LinkedList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class StudentDaoImp extends DaoImpl<Student> implements StudentDao{

    @Override
    public void addStudent(Student student) {
        save(student);
    }

    @Override
    public void addAllStudent(Collection<Student> students){
        for (Student student : students) {
            save(student);
        }
    }

    @Override
    public void updateStudent(Student student) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();// 业务开头
        if(student.getId() == null){
            Student istudent = getStudentBySn(student.getSn());
            int id = istudent.getId();
            getSession().evict(istudent);
            student.setId(id);
        }
        getSession().update(student);
        transaction.commit();
    }

    @Override
    public void deleteStudent(Student dStudent) {
        if(dStudent.getId() == null){
            dStudent = getStudentBySn(dStudent.getSn());
        }
        delete(dStudent);
    }

    @Override
    public void deleteAllStudent(Collection<Student> students) {
        for (Student student : students) {
            delete(student);
        }
    }

    @Override
    public void deleteStudentBySn(String stuSn) {
        Query query = getSession().getNamedQuery("Student.findBySn");
        query.setString("sn", stuSn);
        Student student = (Student) query.uniqueResult();
        getSession().delete(student);
    }

    @Override
    public void deleteAllStudentBySn(Collection<String> stuSns) {
        for (String stuSn : stuSns) {
            deleteStudentBySn(stuSn);
        }
    }

    @Override
    public Student getStudentBySn(String stuSn) {
        Query query = getSession().getNamedQuery("Student.findBySn");
        query.setString("sn", stuSn);
        return (Student) query.uniqueResult();
    }

    @Override
    public Collection<Student> getAllStudentBySn(Collection<String> stuSns) {
        LinkedList<Student> linkedList = new LinkedList<>();
        for (String student : stuSns) {
            linkedList.add(getStudentBySn(student));
        }
        return linkedList;
    }

    @Override//未测试
    public void selectCourse(Student student, Selectcourse selectcourse) {
        create(student);
        student.getSelectcourseCollection().add(selectcourse);
        
    }

}
