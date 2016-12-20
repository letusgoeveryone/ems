package com.letusgo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.daoImp.DaoImpl;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DCollege;
import com.letusgo.dto.DTeacher;
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
	/**
	 * 获取所有教师
	 * @return所有教师list
	 */
	 public List<DTeacher> getAllTeacher(){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		List<Teacher> list=HibernateUtil.getSessionFactory().getCurrentSession()
				.createSQLQuery("select * from teacher;").addEntity(com.letusgo.model.Teacher.class).list();
		List<DTeacher> list2=new ArrayList<DTeacher>();
		for (Teacher teacher : list) {
			if (teacher.getCollege()==null) {
				list2.add(new DTeacher(teacher.getId(), teacher.getSn(), teacher.getName(), teacher.getPassword(),
						teacher.getSex(), teacher.getAvatarid(), teacher.getTel(), teacher.getQq(), teacher.getEmail(),
						teacher.getRegdate(), teacher.getRoleid()));
			} else {
				list2.add(new DTeacher(teacher.getId(), teacher.getSn(), teacher.getName(), teacher.getPassword(),
						teacher.getSex(), teacher.getAvatarid(), teacher.getTel(), teacher.getQq(), teacher.getEmail(),
						teacher.getRegdate(), teacher.getRoleid(),teacher.getCollege().getId(),teacher.getCollege().getName()));
			}
		}
		beginTransaction.commit();//业务结尾
		return list2;
	 }
	
	/**
	 * 获取某个老师信息
	 * @param teacher_sn 教师工号
	 * @return一个教师对象
	 */
	 public DTeacher findateacher(String teacher_sn){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		Teacher teacher=teacherDaoImp.getTeacherBySn(teacher_sn);
		DTeacher dteacher=new DTeacher(teacher.getId(), teacher.getSn(), teacher.getName(), teacher.getPassword(),
				teacher.getSex(), teacher.getAvatarid(), teacher.getTel(), teacher.getQq(), teacher.getEmail(),
				teacher.getRegdate(), teacher.getRoleid());
		if (teacher.getCollege()!=null) {
			dteacher.setCollege(teacher.getCollege().getName());
			dteacher.setCollegeid(teacher.getCollege().getId());
		}
		beginTransaction.commit();//业务结尾   
		return dteacher;
	 }
	 
	 /**
	  * 设置教务管理员
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public String setAcdemicDean(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 
		 int roleValue;
		try {
			roleValue = Integer.valueOf(teacher.getRoleid());
			if (roleValue<=0 ||roleValue>7) {
				 return "role error";
			 }
			 if (roleValue==1) {
				teacher.setRoleid("3");
			 }else{
				char[] ch= Integer.toBinaryString(roleValue).toCharArray();
		         if(String.valueOf(ch[ch.length-2]).equals("1")){
		        	 return "false:ready exist";
		        }else{
		        	teacher.setRoleid(Integer.toString(roleValue+2));
		        }
			}
		} catch (NumberFormatException e) {
			teacher.setRoleid("3");
		}
		 
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }
	 
	 /**
	  * 取消设置教务管理员
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public String removeAcdemicDean(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 int roleValue=Integer.valueOf(teacher.getRoleid());
		 if (roleValue<=0 ||roleValue>7) {
			 return "role error";
		 }
		 if (roleValue==3) {
			teacher.setRoleid("1");
		 }else{
			char[] ch= Integer.toBinaryString(roleValue).toCharArray();
	         if(String.valueOf(ch[ch.length-2]).equals("1")){
	        	 return "false:ready exist";
	        }else{
	        	teacher.setRoleid(Integer.toString(roleValue-2));
	        }
		}
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }	 
	 /**
	  * 设置系统管理员
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public String setAdmin(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 
		 int roleValue;
		try {
			roleValue = Integer.valueOf(teacher.getRoleid());
			if (roleValue<=0 ||roleValue>7) {
				 return "role error";
			 }
			 if (roleValue<4) {
				teacher.setRoleid(Integer.toString(roleValue+4));
			 }else{
				return "false:ready exist";
		     }
		} catch (NumberFormatException e) {
			teacher.setRoleid("5");
		}
		 
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }
	 
	 /**
	  * 取消设置系统管理员
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public String removeAdmin(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 int roleValue=Integer.valueOf(teacher.getRoleid());
		 if (roleValue<=0 ||roleValue>7) {
			 return "role error";
		 }
		 if (roleValue==5) {
			teacher.setRoleid("1");
		 }else{
			 if (roleValue<4) {
				 	return "false:ready exist";
				 }else{
					teacher.setRoleid(Integer.toString(roleValue-4));	
			     }
		}
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }	
}
