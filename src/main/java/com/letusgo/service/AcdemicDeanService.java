package com.letusgo.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.daoImp.CourseDaoImp;
import com.letusgo.daoImp.DaoImpl;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.dto.DTeacherClass;
import com.letusgo.dto.DTermCourseMaster;
import com.letusgo.dto.DTermTeacher;
import com.letusgo.model.College;
import com.letusgo.model.Course;
import com.letusgo.model.Teacher;
import com.letusgo.model.Termcourse;
import com.letusgo.model.Termteacher;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class AcdemicDeanService {
	/**
	 * 获取所有教师
	 * @return所有教师list
	 */
	 public List<DTeacher> getAllTeacher(int collegeid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Teacher t WHERE t.college.id = :collegeId");
		query.setInteger("collegeId", collegeid);
		List<Teacher> list=query.list();
		List<DTeacher> list2=new ArrayList<DTeacher>();
		for (Teacher teacher : list) {
			list2.add(new DTeacher(teacher.getId(), teacher.getSn(), teacher.getName(), teacher.getPassword(),
					teacher.getSex(), teacher.getAvatarid(), teacher.getTel(), teacher.getQq(), teacher.getEmail(),
					teacher.getRegdate(), teacher.getRoleid()));
		}
		transaction.commit();
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
	  * 删除一个教师
	  * @param teacher_sn 教师工号
	  * @return删除是否成功
	  */
	 public String deleteTeacher(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 teacherDaoImp.deleteTeacherBySn(teacher_sn);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }
	 
	 /**
	  * 修改一个老师（改密码用）
	  * @param teacher_sn 教师工号
	  * @return修改是否成功
	  */
	 public String modifyTeacher(String teacher_sn){
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
		 Teacher teacher =teacherDaoImp.getTeacherBySn(teacher_sn);
		 teacher.setPassword((new GeneralService()).getMD5(teacher.getSn()));
		 teacherDaoImp.save(teacher);
		 beginTransaction.commit();//业务结尾
		 return "true";
	 }
	 
	 /**
	  * 获取某学院某学期课程
	  * @param collegeid 学院id
	  * @param term 学期
	  * @return某学院所有课程list
	  */
	 public List<DTermCourseMaster> GetTermCourse(int collegeid,String term){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Termcourse t WHERE t.term = :term and t.course.college.id = :collegeId");
		query.setString("term", term);
		query.setInteger("collegeId", collegeid);
		List<Termcourse> list = query.list();
		System.out.println("term"+term+";collegeid"+collegeid);
		List<DTermCourseMaster> list2 = new ArrayList<DTermCourseMaster>();
		for(Termcourse termcourse : list){
			DTermCourseMaster temp = new DTermCourseMaster();
			temp.setCourseName(termcourse.getCourse().getName());
			temp.setNumber(termcourse.getCourse().getNumber());
			temp.setId(termcourse.getId());
			if (termcourse.getTeacher()!=null) {
				temp.setSn(termcourse.getTeacher().getSn());
				temp.setTeaName(termcourse.getTeacher().getName());
			}
			list2.add(temp);
		}
		transaction.commit();
		return list2;
	 }
	 
	  public List<DTermTeacher> getTermTeacher(int termCourseid)
	  {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction transaction = session.beginTransaction();
	    Query query = session.createQuery("FROM Termteacher t WHERE t.termcourse.id = :termcourseid");
	    query.setInteger("termcourseid", termCourseid);
	    List<Termteacher> list = query.list();
	    List<DTermTeacher> list2 = new ArrayList();
	    for (Termteacher termteacher : list) {
	      DTermTeacher temp = new DTermTeacher(termteacher.getId().intValue(), termteacher.getTeacher().getSn(), termteacher.getTeacher().getName(), termteacher.getMaxclass().intValue());
	      list2.add(temp);
	    }
	    transaction.commit();
	    return list2;
	  }

	  public String setTermTeacher(int termCourseid, int maxclass, String teachersn)
	  {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction transaction = session.beginTransaction();
	    Termteacher termteacher = new Termteacher();
	    termteacher.setTeacher(new TeacherDaoImp().getTeacherBySn(teachersn));
	    termteacher.setMaxclass(Integer.valueOf(maxclass));
	    termteacher.setTermcourse((Termcourse)new DaoImpl().find(Termcourse.class, termCourseid));
	    session.save(termteacher);
	    transaction.commit();
	    return "true";
	  }
	 
	 
	/**
	 * 列出某学院所有课程（包含未开课课程）
	 * @return所有教师list
	 */
	 public List<DTermCourseMaster> getCollegeCourse(int collegeid){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Course t WHERE t.college.id = :collegeId");
			query.setInteger("collegeId", collegeid);
			List<Course> list = query.list();
			List<DTermCourseMaster> list2 = new ArrayList<DTermCourseMaster>();
			for(Course course : list){
				DTermCourseMaster temp = new DTermCourseMaster();
				temp.setCourseName(course.getName());
				temp.setNumber(course.getNumber());
				temp.setId(course.getId());
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
	 public String addCourse(int collegeid,String courseNmber,String courseName){
			Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
			Course course=new Course(courseNmber, courseName,new DaoImpl<College>().find(College.class, collegeid));
			CourseDaoImp courseDaoImp= new CourseDaoImp();
			courseDaoImp.addCourse(course);
			beginTransaction.commit();//业务结尾
		 return "true";
	 }
	 
	 /**
	  * 删除某学院某课程
	  * @param college
	  * @param course
	  * @return
	  */
	 public String deleteCourse(int collegeId,int courseid){
			Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
			Course course=new DaoImpl<Course>().find(Course.class, courseid);
			if (course.getCollege().getId()==collegeId) {
				CourseDaoImp courseDaoImp=new CourseDaoImp();
				courseDaoImp.deleteCourse(course);
			}
			beginTransaction.commit();//业务结尾
		 return "true";
	 }

	 /**
	  * 设置课程负责人<br>
	  * 注意检查是否有同一个学期同一门课程两个老师作为课程负责人 如果有  则覆盖。好  还有么 没了就撤了
	  * @param term学期
	  * @param courseid课程id
	  * @param mastersn课程负责人工号
	  * @return是否成功
	  */
	 public String setCourseMaster(String term,int courseid,String mastersn){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Termcourse termcourse = new Termcourse();
			termcourse.setTeacher(new TeacherDaoImp().getTeacherBySn(mastersn));
			termcourse.setTerm(term);
			termcourse.setCourse(new CourseDaoImp().find(Course.class, courseid));
			session.save(termcourse);
			transaction.commit();
		 return "true";
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
	 public String setCourseTeacher(String term,int courseid,String teachersn,int classnumber){
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
		 return "true";
	 }
	 
	 /**
	  *  获取某课由谁来教，有哪些班级
	  * @param term学期
	  * @param courseid课程id
	  * @return 所有教这门课的老师和班级
	  */
	@RequestMapping("/getcourseteacher")
	@ResponseBody
	public List<DTeacherClass> getcourseteacher(String term,int courseid){

		return null;
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
	 public String setCurrentTerm (int collegeId,String term){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 College college=new DaoImpl<College>().find(College.class, collegeId);
		 college.setCurrterm(term);
		 session.save(college);
		 transaction.commit();
		 return "true";
	 
	 }
	 /**
	  * 新增某学院的学期课程
	  * @param teachersn负责人工号
	  * @param courseId课程id 
	  * @param term学期
	  * @return
	  */
	 public String setTermCourse(String teachersn,String courseId,String term){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 TeacherDaoImp teacherDaoImp=new TeacherDaoImp();
		 Teacher teacher=teacherDaoImp.getTeacherBySn(teachersn);
		 teacherDaoImp.setTermCourse(teacher.getId(), Integer.valueOf(courseId), term);
		 transaction.commit();
		 return "true";
	 
	 }
	 
	 /**
	  * 批量新增课程
	  * @param batchcourse
	  * @return
	 * @throws Exception 
	  */
	 public String batchAddCourse(MultipartFile file,int collegeid) throws Exception{
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		 if(!file.isEmpty()){         
			 try {  
				 InputStream in;  
				 try (FileOutputStream os = new FileOutputStream("/"+file.getOriginalFilename())) {
					 in = file.getInputStream();
					 Workbook wb = null;
					 List<Course> list=new ArrayList<Course>();
					 College college=(new DaoImpl<College>()).find(College.class, collegeid);
					 if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
						 wb = new XSSFWorkbook(in);
					 } else {
						 wb = new HSSFWorkbook(in);
					 }
				Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
				Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
				while (rows.hasNext()) {
					Row row = rows.next(); // 获得行数据
					if (row.getRowNum()>0) {// 获得行号从0开始
						Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
						Course tmpcourse=new Course();
						while (cells.hasNext()) {
							Cell cell = cells.next();
							if (cell.getColumnIndex()==0) {
								tmpcourse.setNumber(cell.getStringCellValue());
							}
							if (cell.getColumnIndex()==1) {
								tmpcourse.setName(cell.getStringCellValue());
							}
						}
						if (!(tmpcourse.getName()==null || tmpcourse.getNumber()==null)) {
							tmpcourse.setCollege(college);
							list.add(tmpcourse);
						}
						
					}
				}
				CourseDaoImp courseDaoImp= new CourseDaoImp();
				courseDaoImp.addAllCourse(list);
		    }  
		    in.close();
		    beginTransaction.commit();//业务结尾
		    return "true";
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        }  
		  }
		  beginTransaction.commit();//业务结尾
		  return "has Exception";
	 }
	 
	 /**
	  * 批量新增教师
	  * @param batchteacher
	  * @return
	  * @throws Exception 
	  */
	 public String batchAddTeacher(MultipartFile file,int collegeid) throws Exception{
		 Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
	      if(!file.isEmpty()){         
	            try {  
	                InputStream in;  
	                try (FileOutputStream os = new FileOutputStream("/"+file.getOriginalFilename())) {
	                    in = file.getInputStream();
	                    Workbook wb = null;
	                    List<Teacher> list=new ArrayList<Teacher>();
	                    College college=(new DaoImpl<College>()).find(College.class, collegeid);
	                    if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
	                    	wb = new XSSFWorkbook(in);
						} else {
							wb = new HSSFWorkbook(in);
						}
	        			Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
	        			Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
	        			while (rows.hasNext()) {
	        				Row row = rows.next(); // 获得行数据
	        				if (row.getRowNum()>0) {// 获得行号从0开始
		        				Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
		        				Teacher tmpteacher=new Teacher();
		        				while (cells.hasNext()) {
		        					Cell cell = cells.next();
		        					if (cell.getColumnIndex()==0) {
		        						tmpteacher.setSn(cell.getStringCellValue());
									}
		        					if (cell.getColumnIndex()==1) {
		        						tmpteacher.setName(cell.getStringCellValue());
									}
		        					if (cell.getColumnIndex()==2) {
		        						tmpteacher.setPassword(new GeneralService().getMD5(cell.getStringCellValue()));
									}
		        				}
		        				if (!(tmpteacher.getName()==null || tmpteacher.getSn()==null || tmpteacher.getPassword()==null)) {
		        					tmpteacher.setCollege(college);
		        					list.add(tmpteacher);
								}
		        				
							}
	        			}
	        			TeacherDaoImp teacherDaoImp =new TeacherDaoImp();
	        			teacherDaoImp.addAllTeacher(list);
	                }
	                in.close(); 
	                beginTransaction.commit();//业务结尾
	                return "true";
	            } catch (FileNotFoundException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
		  }
		  beginTransaction.commit();//业务结尾
		  return "has Exception";
	 }
}