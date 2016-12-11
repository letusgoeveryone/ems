package com.letusgo.service;

import java.util.List;
import org.hibernate.Transaction;
import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.model.Course;
import com.letusgo.model.Teacher;


public class AcdemicDeanService {
	/**
	 * 获取所有教师
	 * @return所有教师list
	 */
	 public List<DTeacher> GetAllTeacher(){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		List<DTeacher> list=HibernateUtil.getSessionFactory().getCurrentSession()
				.createSQLQuery("select * from teacher;").addEntity(com.letusgo.model.Teacher.class).list();
		beginTransaction.commit();//业务结尾
//		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
//		List<DTeacher> list=HibernateUtil.getSessionFactory().getCurrentSession()
//				.createQuery("select new com.letusgo.dto.DTeacher(t.id,t.sn,t.name,t.password,t.sex,t.avatarid,t.tel,t.qq,t.email,t.regdate,t.roleid,t.college.id) from Teacher t").list();
//		beginTransaction.commit();//业务结尾   
		return list;
	 }
	 
	 /**
	  * 新增一个教师
	  * @param teacher传入一个非空Teacher
	  * @return新增是否成功
	  */
	 public boolean addTeacher(String sn,String name,String password){
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		Teacher teacher=new Teacher(sn, name, password);
		TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		teacherDaoImp.addTeacher(teacher);
		beginTransaction.commit();//业务结尾
		return true;
	 }
	 
	 
	 /**
	  * 删除一个教师
	  * @param teacher传入一个非空Teacher
	  * @return删除是否成功
	  */
	 public boolean deleteTeacher(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 teacherDaoImp.deleteTeacherBySn(teacher_sn);
		 beginTransaction.commit();//业务结尾
		 return true;
	 }
	 
	 /**
	  * 修改一个老师（改密码用）
	  * @param teacher传入一个非空Teacher
	  * @return修改是否成功
	  */
	 public boolean modifyTeacher(Teacher teacher){
		 return true;
	 }
	 
	 /**
	  * 获取某学院所有课程
	  * @param college 学院
	  * @return某学院所有课程list
	  */
	 public List<Course> GetAllCourse(String college){
		 return null;
	 }
	 
	 /**
	  * 新增某学院某课程
	  * @param college 学院
	  * @param course 课程
	  * @return是否成功
	  */
	 public boolean addCourse(String college,Course course){
		 return true;
	 }
	 
	 /**
	  * 删除某学院某课程
	  * @param college
	  * @param course
	  * @return
	  */
	 public boolean deleteCourse(String college,Course course){
		 return true;
	 }
	 /**
	  * 修改某学院某课程
	  * @param college 学院
	  * @param course 课程
	  * @return 是否成功
	  */
	 public boolean modifyCourse(String college,Course course){
		 return true;
	 }
		
	 
	 /**
	  * 设置课程负责人
	  * @param college学院
	  * @param course课程
	  * @param teacher教师
	  * @return是否成功
	  */
	 public boolean setCourseMaster(String college,Course course,Teacher teacher){
		 return true;
	 }
	 
	 /**
	  * 获取课程负责人
	  * @param college学院
	  * @param course课程
	  * @param teacher教师
	  * @return课程负责人Teacher对象
	  */
	 public Teacher getCourseMaster(String college,Course course){
		 return null;
	 }	 
	 
	 /**
	  * 设置某课由谁来教 或 设置某老师教什么课
	  * @param college学院
	  * @param course课程
	  * @param teacher教师
	  * @return是否成功
	  */
	 public boolean setCourseTeacher(String college,Course course,Teacher teacher){
		 return true;
	 }
	 
	 /**
	  * 设置某课由谁来教 或 设置某老师教什么课,并设置这门课这位老师下面的班级数
	  * @param college学院
	  * @param course课程
	  * @param teacher教师
	  * @param classNumber这门课这位老师下面的班级数
	  * @return是否成功
	  */
	 public boolean setCourseTeacher(String college,Course course,Teacher teacher,int classNumber){
		 return true;
	 }
	 
	 /**
	  * 设置某课由谁来教 或 设置某老师教什么课,并设置这门课这位老师下面的班级数
	  * @param college学院
	  * @param course课程
	  * @param teacher教师
	  * @return 这门课这位老师下面的班级数
	  */
	 public int getCourseTeacherClassNumber(String college,Course course,Teacher teacher){
		 return -1;
	 }
	 
	 /**
	  * 获取某学院当前学期
	  * @param college学院
	  * @return当前学期
	  */
	 public String getCurrentTerm (String college){
		 return null;
	 }
	 
	 /**
	  * 设置某学院的当前学期
	  * @param college学院
	  * @param term学期
	  * @return
	  */
	 public boolean setCurrentTerm (String college,String term){
		 return true;
	 
	 }
}
