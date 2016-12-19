package com.letusgo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.daoImp.DaoImpl;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DCollege;
import com.letusgo.model.College;
import com.letusgo.model.Teacher;

public class AdminService {
	/**
	 * 获取所有学院
	 * @return所有教师list
	 */
	 public List<DCollege> getAllCollege(){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		List<College> list=HibernateUtil.getSessionFactory().getCurrentSession()
				.createSQLQuery("select * from college;").addEntity(com.letusgo.model.College.class).list();
		List<DCollege> list2=new ArrayList<DCollege>();
		for (College college : list) {
			list2.add(new DCollege(college.getId(),college.getName(),college.getCurrterm()));
		}
		beginTransaction.commit();//业务结尾
		return list2;
	 }
	 /**
	  * 新增一个教师
	  * @param sn 教师工号
	  * @param name 教师姓名
	  * @param password 教师密码
	  * @param collegeid 学院id
	  * 
	  * @return新增是否成功
	  */
	 public String addTeacher(String sn,String name,String password,int collegeid){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		Teacher teacher=new Teacher(sn, name, password);
		teacher.setCollege(new DaoImpl<College>().find(College.class, collegeid));
		TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		teacherDaoImp.addTeacher(teacher);
		beginTransaction.commit();//业务结尾
		return "true";
	 }
	 /**
	  * 新增一个学院
	  * @param name 学院名
	  * @param currterm 学员当前学期
	  * 
	  * @return新增是否成功
	  */
	 public String addCollege(String name,String currterm){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		College college=new College(name);
		college.setCurrterm(currterm);
		CollegeDaoImp collegeDaoImp= new CollegeDaoImp();
		collegeDaoImp.addCollege(college);
		beginTransaction.commit();//业务结尾
		return "true";
	 }
}
