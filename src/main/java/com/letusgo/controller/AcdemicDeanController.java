package com.letusgo.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.DaoImpl;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.dto.DTeacherClass;
import com.letusgo.dto.DTermCourseMaster;
import com.letusgo.dto.DTermTeacher;
import com.letusgo.model.College;
import com.letusgo.model.Course;
import com.letusgo.model.Teacher;
import com.letusgo.service.AcdemicDeanService;
import com.letusgo.service.GeneralService;


@Controller
@RequestMapping("/acdemicdean/")
public class AcdemicDeanController {
	/***
	 * 响应acdemicdean页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "acdemicdean";
	}

	/*新增教师
	  * 获取参数sn,name,password
	  * 返回值true,false
	  * 可能异常：UnsupportedEncodingException
	  * */
	@RequestMapping("/addteacher")
	@ResponseBody
	public String addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		String name= request.getParameter("name");
		String password= request.getParameter("password");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.addTeacher(sn, name, password,(new GeneralService().getCurrentUserCollegeid()));
	}
	
	 /*删除教师
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/deleteteacher")
	@ResponseBody
	public String deleteTeacher(HttpServletRequest request, HttpServletResponse response){
		String sn= request.getParameter("sn");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.deleteTeacher(sn);
	}
	
	 /*修改教师
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/modifyteacher")
	@ResponseBody
	public String modifyTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.modifyTeacher(sn);
	}
	
	 /* 列出本学院所有教师
	  * 返回值
	  * */
	@RequestMapping("/getallteacher")
	@ResponseBody
	public List<DTeacher> getAllTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.getAllTeacher(collegeid);
	}
	
	 /* 通过工号读取教师信息
	  * 参数sn
	  * 返回值json
	  * */
	@RequestMapping("/findateacher")
	@ResponseBody
	public DTeacher findATeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.findateacher(sn);
	}
	
	 /* 列出某学院某学期所有课程和负责人信息
	  * 参数term
	  * 返回值json
	  * */
	@RequestMapping("/termcoursemaster")
	@ResponseBody
	public List<DTermCourseMaster> getTermCourseMaster(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.GetTermCourse(collegeid, term);
	}
	
	 /* 列出某学院所有课程（包含未开课课程）
	  * 无参数
	  * 返回值json
	  * */
	@RequestMapping("/getcollegecourse")
	@ResponseBody
	public List<DTermCourseMaster> CollegeCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.getCollegeCourse(collegeid);
	}
	
	 /* 新增某学院课程（成功会生成courseid，批量添加入口稍后添加）
	  * 参数number,name（collegeId学院id从后台取）
	  * 返回值true,false
	  * */
	@RequestMapping("/addcourse")
	@ResponseBody
	public String addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		String number= request.getParameter("number");
		String name= request.getParameter("name");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.addCourse(collegeid, number, name);
	}

	 /* 删除某学院课程
	  * 参数courseid（collegeId学院id从后台读取）
	  * 返回值true,false
	  * */
	@RequestMapping("/deletecourse")
	@ResponseBody
	public String deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String courseid= request.getParameter("courseid");
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.deleteCourse(Integer.valueOf(courseid), collegeid);
	}
	
	 /* 设置某学期下，（某学院）某课程的课程负责人
	  * 参数term, courseid, mastersn（collegeId学院id从后台取）
	  * 返回值true,false
	  * */
	@RequestMapping("/setcoursemaster")
	@ResponseBody
	public String setCourseMaster(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		String mastersn= request.getParameter("mastersn");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.setCourseMaster(term, Integer.valueOf(courseid), mastersn);
	}
	 /* 设置某课由谁来教 或 设置某老师教什么课,并设置这门课这位老师下面的班级数
	  * 参数term, courseid, teachersn, classnumber（collegeId学院id从后台取, classnumber为-1则不限制）
	  * 返回值true,false
	  * */
	@RequestMapping("/setcourseteacher")
	@ResponseBody
	public String getCourseTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		String teachersn= request.getParameter("teachersn");
		String classnumber= request.getParameter("classnumber");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.setCourseTeacher(term, Integer.valueOf(courseid), teachersn, Integer.valueOf(classnumber));
	}
	
	 /* 获取某课由谁来教，有哪些班级
	  * 参数term, courseid（collegeId学院id从后台取, classnumber为-1则不限制）
	  * 返回值
	  * 
	@RequestMapping("/getcourseteacher")
	@ResponseBody
	public List<DTeacherClass> getcourseteacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		//待鸿运实现
		return null;
	}
	*/
	 /* 获取某课由谁来教，有哪些班级
	  * 参数term, courseid（collegeId学院id从后台取, classnumber为-1则不限制）
	  * 返回值
	  * */
	@RequestMapping("/gettermteacher")
	@ResponseBody
	public List<DTermTeacher> gettermteacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String termCourseid= request.getParameter("termcourseid");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.getTermTeacher(Integer.valueOf(termCourseid));
	}	
	
	
	@RequestMapping("/settermteacher")
	@ResponseBody
	public String settermteacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");

		String termcourseid= request.getParameter("termcourseid");
		String maxclass= request.getParameter("maxclass");
		String teachersn= request.getParameter("teachersn");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.setTermTeacher(Integer.valueOf(termcourseid), Integer.valueOf(maxclass), teachersn);
	}	
	
	 /* 新增某学院某学期课程
	  * 参数number,name（collegeId学院id从后台取）
	  * 返回值true,false
	  * */
	@RequestMapping("/settermcourse")
	@ResponseBody
	public String setTermCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String teachersn= request.getParameter("teachersn");
		String courseId= request.getParameter("courseId");
		String term= request.getParameter("term");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.setTermCourse(teachersn, courseId, term);
	}
	
	 @RequestMapping(value = "/batchaddcourse",method = RequestMethod.POST)
	 public @ResponseBody String course_submit(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws  Exception {
		int collegeid=(new GeneralService().getCurrentUserCollegeid());
		AcdemicDeanService acdemicDeanService=new AcdemicDeanService();
		return acdemicDeanService.batchAddCourse(file,collegeid);
	  }
	  
	 @RequestMapping(value = "/batchaddteacher",method = RequestMethod.POST)
	 public @ResponseBody String teacher_submit(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws  Exception {
		 int collegeid=(new GeneralService().getCurrentUserCollegeid());
		 AcdemicDeanService acdemicDeanService=new AcdemicDeanService();
		return acdemicDeanService.batchAddTeacher(file,collegeid);
	  }
	  
	
	
}
