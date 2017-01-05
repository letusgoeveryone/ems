package com.letusgo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.dao.StudentDao;
import com.letusgo.dao.TeacherDao;
import com.letusgo.daoImp.CourseDaoImp;
import com.letusgo.daoImp.StudentDaoImp;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.model.College;
import com.letusgo.model.Student;
import com.letusgo.model.Teacher;
import com.letusgo.model.Termclass;
import com.letusgo.model.Termcourse;

public class StudentService {
	/**
	 * 学生登录
	 * 
	 * @param userName
	 *            用户名，填写学号(10位)
	 * 
	 * @param stuPwd
	 *            密码
	 * 
	 * @return 用户名正确，不重复，且与相应的密码吻合则返回true，否则返回false
	 */
	// 根据学号，密码登录认证
	public static boolean studentSignInByUserName(String userName, String stuPwd) {
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		Student student = studentDaoImp.getStudentBySn(userName);
		if (userName.equals(student.getSn())) {
			return true;
		}
		return false;
	}

	/**
	 * 学生密码找回？
	 * 
	 * @param userName
	 *            用户名，填写学号（10位），手机号（11位），身份证号（其它位数）都可以
	 * 
	 *
	 * @return 成功返回学生密码，失败返回提示
	 */

	public static String studentLoginGetPasswordByUserName(String userName) {
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		Student student = studentDaoImp.getStudentBySn(userName);
		if (student != null) {
			return student.getPassword();
		} else {
			return "获取学生对象失败";
		}
	}
	/**
	 * 为所有学生分页
	 * 
	 * @param pc当前页
	 * 
	 * @param ps每页记录数
	 * 
	 * @return 返回一个分页bean对象
	 */
	// public static PageBean<Student> findAll(Integer pc, Integer ps){
	// return StudentDao.findAll(pc, ps);
	// }

	/**
	 * 将一个临时学生加入到正式学生中
	 *
	 * @param tempStudent
	 *            临时学生对象
	 */
	// public static void addStudentFromtempStudent(TempStudent tempStudent) {
	// StudentDao.addStudentFromtempStudent(tempStudent);
	// }

	/**
	 * 获取全部正式学生
	 * 
	 * @return 返回全体正式学生的集合（不含密码）
	 */
	public List getAllStudent(Collection<String> stuSns) {
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		return (List) studentDaoImp.getAllStudentBySn(stuSns);
	}

	/**
	 * 根据id将一个临时学生加入到正式学生中
	 * 
	 * @param id
	 *            临时学生id
	 */
	// public void addStudentFromtempStudent(Integer id) {
	// StudentDao.addStudentFromtempStudent(TempStudentDao.getTempStudentById(id));
	// }

	/**
	 * 根据学号获取一个学生的信息
	 * 
	 * @param 学生学号sn
	 * @return 学生对象
	 */
	public Student getStudentInfo(String sn) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		Student std = studentDaoImp.getStudentBySn(sn);
		transaction.commit();
		return std;
	}

	/**
	 * 密码修改
	 * 
	 * @param 学生学号sn
	 * @param 第一次输入的密码
	 * @param 第二次输入的密码
	 */
	public void modifyPwdBySnAndPwd(String sn, String pw, String repw) {
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//Transaction transaction = session.beginTransaction();// 业务开头
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		Student stu = new Student();
		stu = getStudentInfo(sn);
		stu.setPassword(repw);
		studentDaoImp.updateStudent(stu);
		//transaction.commit();// 业务结尾

	}

	/**
	 * 个人信息的修改
	 *
	 * @param Student对象
	 */
	public void modifyInfoBySn(Student student) {
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();// 业务开头
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		// Student std = studentDaoImp.getStudentBySn(sn);

		studentDaoImp.updateStudent(student);
		beginTransaction.commit();// 业务结尾
	}

	/**
	 * 学生头像ID修改
	 * 
	 * @param 学生学号
	 *            sn
	 * @param 当前头像ID
	 *            avatarid
	 *
	 */
	public void ModifyStuAvatarId(String sn, String avatarid) {
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();// 业务开头
		StudentDaoImp studentDaoImp = new StudentDaoImp();
		Student std = studentDaoImp.getStudentBySn(sn);
		std.setAvatarid(avatarid);
		studentDaoImp.updateStudent(std);
		beginTransaction.commit();// 业务结尾
	}

	/**
	 * 返回课程页详情
	 * 
	 * @param 课程号scid
	 * @return 返回教师对象
	 */
	@Test
	public Teacher getTeaByScid(String scid) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Termcourse t WHERE t.id = :courseId");

		query.setInteger("courseId", Integer.parseInt(scid));
		Termcourse termcourse = (Termcourse) query.uniqueResult();
		termcourse.getId();
		System.out.println(termcourse.getId());
		// TeacherDaoImp teacherDaoImp = new TeacherDaoImp();
		// 通过课程号获取教师号
		// TODO
		// String TecSn = CourseDaoImp.getTecsnByCourseId(scid);
		// String TecSn = null;
		Teacher tec = termcourse.getTeacher();
		return tec;
	}

	/**
	 * 通过课程号返回学期
	 * 
	 * @param 课程号scid
	 *
	 */
	public String getTermByScid(String scid) {
		// 通过课程号获取学期
		// TODO
		// String term = TermCourseDao.getxueqiBySCId(scid).toString();
		String term = null;
		return term;
	}

	/**
	 * 通过课程号返回课程名称
	 * 
	 * @param 课程号scid
	 *
	 */
	public String getCourseNameByScid(String scid) {
		CourseDaoImp courseDaoImp = new CourseDaoImp();
		// 根据课程号获取课程名称
		String courseName = null;
		// TODO
		// String courseName = courseDaoImp.get
		return courseName;

	}

	/**
	 * 通过学期号和学号来获取学生选的课
	 * 
	 * @param 学期号
	 * @param 学号
	 */
	// TODO
	public List getStuSelectedCourseByTermIdAndCourseId(String term, String stusn) {
		// TODO
		// List list =
		// StudentSelectCourseDao.getStudentSelectCourseNameByTermSnCourseId2(term,
		// stusn);
		List list = null;
		return list;
	}

}
