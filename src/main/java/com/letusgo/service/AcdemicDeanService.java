package com.letusgo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.Null;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.daoImp.CourseDaoImp;
import com.letusgo.daoImp.DaoImpl;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.dto.TermCourseMaster;
import com.letusgo.model.College;
import com.letusgo.model.Course;
import com.letusgo.model.Teacher;
import com.letusgo.model.Termcourse;
import com.letusgo.model.Termteacher;


public class AcdemicDeanService {
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
//			DTeacher tmp=new DTeacher();
//			tmp.setName(teacher.getName());
//			tmp.setId(teacher.getId());
//			tmp.setSn(teacher.getSn());
//			tmp.setPassword(teacher.getPassword());
//			list2.add(tmp);
			list2.add(new DTeacher(teacher.getId(), teacher.getSn(), teacher.getName(), teacher.getPassword(),
					teacher.getSex(), teacher.getAvatarid(), teacher.getTel(), teacher.getQq(), teacher.getEmail(),
					teacher.getRegdate(), teacher.getRoleid()));
			
		}
		beginTransaction.commit();//业务结尾
//		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
//		List<DTeacher> list=HibernateUtil.getSessionFactory().getCurrentSession()
//				.createQuery("select new com.letusgo.dto.DTeacher(t.id,t.sn,t.name,t.password,t.sex,t.avatarid,t.tel,t.qq,t.email,t.regdate,t.roleid,t.college.id) from Teacher t").list();
//		beginTransaction.commit();//业务结尾   
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
			beginTransaction.commit();//业务结尾   
			return dteacher;
		 }
	 /**
	  * 新增一个教师
	  * @param sn 教师工号
	  * @param name 教师姓名
	  * @param password 教师密码
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
	  * @param teacher_sn 教师工号
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
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public boolean modifyTeacher(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 teacher.setPassword(teacher.getSn());
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return true;
	 }
	 
	 /**
	  * 获取某学院所有课程
	  * @param collegeid 学院id
	  * @param term 学期
	  * @return某学院所有课程list
	  */
	 public List<TermCourseMaster> GetAllCourse(int collegeid,String term){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Termcourse t WHERE t.term = :term and t.courseid.college.id = :collegeId");
		query.setString("term", term);
		query.setInteger("collegeId", collegeid);
		List<Termcourse> list = query.list();
		List<TermCourseMaster> list2 = new ArrayList<TermCourseMaster>();
		for(Termcourse termcourse : list){
			TermCourseMaster temp = new TermCourseMaster();
			temp.setCourseName(termcourse.getCourse().getName());
			temp.setNumber(termcourse.getCourse().getNumber());
			temp.setSn(termcourse.getTeacher().getSn());
			temp.setTeaName(termcourse.getTeacher().getName());
			list2.add(temp);
		}
		transaction.commit();
		return list2;
	 }
	 
	 /**
	  * 新增某学院某课程
	  * @param collegeid 学院ie
	  * @param courseNmber 课程编号
	  * @param courseName 课程名
	  * @return是否成功
	  */
	 public boolean addCourse(int collegeid,String courseNmber,String courseName){
			Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
			Course course=new Course(courseNmber, courseName,collegeid);
			CourseDaoImp courseDaoImp= new CourseDaoImp();
			courseDaoImp.addCourse(course);
			beginTransaction.commit();//业务结尾
		 return true;
	 }
	 
	 /**
	  * 删除某学院某课程
	  * @param college
	  * @param course
	  * @return
	  */
	 public boolean deleteCourse(int collegeId,int courseid){
			Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
			Course course=new DaoImpl<Course>().find(Course.class, courseid);
			if (course.getCollege().getId()==collegeId) {
				CourseDaoImp courseDaoImp=new CourseDaoImp();
				courseDaoImp.deleteCourse(course);
			}
			beginTransaction.commit();//业务结尾
		 return true;
	 }

	 /**
	  * 设置课程负责人<br>
	  * 注意检查是否有同一个学期同一门课程两个老师作为课程负责人 如果有  则覆盖。好  还有么 没了就撤了
	  * @param term学期
	  * @param courseid课程id
	  * @param mastersn课程负责人工号
	  * @return是否成功
	  */
	 public boolean setCourseMaster(String term,int courseid,String mastersn){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Termcourse termcourse = new Termcourse();
			termcourse.setTeacher(new TeacherDaoImp().getTeacherBySn(mastersn));
			termcourse.setTerm(term);
			termcourse.setCourse(new CourseDaoImp().find(Course.class, courseid));
			session.save(termcourse);
			transaction.commit();
		 return true;
	 }
	 
	 /**
	  * 获取课程负责人
	  * @param term 学期
	  * @param int courseid课程id
	  * @return课程负责人sn
	  */
	 public String getCourseMaster(String term , int courseid){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
		 Termcourse termcourse = (Termcourse) session.createQuery("FROM Termcourse t WHERE t.term = :term and t.course.id = :id")
		 .setString("term", term).setInteger("id", courseid).uniqueResult();
			transaction.commit();
		 return termcourse.getTeacher().getSn();
	 }	 
	 
	 
	 /**
	  * 设置某课由谁来教 或 设置某老师教什么课
	  * @param term学期
	  * @param courseid课程id
	  * @param teachersn教师sn
	  * @param classnumber班级数
	  * @return是否成功term, courseid, teachersn, classnumber
	  */
	 public boolean setCourseTeacher(String term,int courseid,String teachersn,int classnumber){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 Termcourse termcourse = (Termcourse) session.createQuery("FROM Termcourse t WHERE t.term = :term and t.course.id = :id")
		 .setString("term", term).setInteger("id", courseid).uniqueResult();
		 Termteacher termteacher=new Termteacher();
		 termteacher.setTermcourse(termcourse);
		 termteacher.setTeacher(new TeacherDaoImp().getTeacherBySn(teachersn));
		 termteacher.setMaxclass(classnumber);
		 session.save(termteacher);
		 transaction.commit();
		 return true;
	 }
	 

	 
	 /**
	  * 设置某课由谁来教 或 设置某老师教什么课,并设置这门课这位老师下面的班级数
	  * @param term学期
	  * @param courseid课程id
	  * @param teachersn教师sn
	  * @return 这门课这位老师下面的班级数
	  */
	 public int getCourseTeacherClassNumber(String term,int courseid,String teachersn){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 Termcourse termcourse = (Termcourse) session.createQuery("FROM Termcourse t WHERE t.term = :term and t.course.id = :id")
		 .setString("term", term).setInteger("id", courseid).uniqueResult();
		 Termteacher termteacher=(Termteacher) session.createQuery("FROM Termteacher t WHERE t.termcourse.id = :tcid and t.teacher.id = :tid")
				 .setInteger("tcid", termcourse.getId()).setString("tid", teachersn).uniqueResult();
		 transaction.commit();
		 return termteacher.getMaxclass();
	 }
	 
	 /**
	  * 获取某学院当前学期
	  * @param college学院
	  * @return当前学期
	  */
	 public String getCurrentTerm (int collegeId){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		College college=new DaoImpl<College>().find(College.class, collegeId);
		beginTransaction.commit();//业务结尾
		 return college.getCurrterm();
	 }
	 
	 /**
	  * 设置某学院的当前学期
	  * @param college学院
	  * @param term学期
	  * @return
	  */
	 public boolean setCurrentTerm (int collegeId,String term){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		College college=new DaoImpl<College>().find(College.class, collegeId);
		college.setCurrterm(term);
		beginTransaction.commit();//业务结尾
		 return true;
	 
	 }
}
