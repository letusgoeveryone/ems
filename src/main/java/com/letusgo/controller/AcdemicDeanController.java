package com.letusgo.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letusgo.dto.DTeacher;
import com.letusgo.dto.TermCourseMaster;
import com.letusgo.model.Teacher;
import com.letusgo.service.AcdemicDeanService;

@Controller
@RequestMapping("/acdemicdean/")
public class AcdemicDeanController {
	
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
		acdemicDeanService.addTeacher(sn, name, password);
		return "true";
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
		acdemicDeanService.deleteTeacher(sn);
		return "true";
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
		acdemicDeanService.deleteTeacher(sn);
		return "true";
	}
	
	 /* 列出所有教师
	  * 返回值
	  * */
	@RequestMapping("/getallteacher")
	@ResponseBody
	public String[] getAllTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
//		List<DTeacher> list=new LinkedList();
//		DTeacher d1=new DTeacher();
//		d1.setId(1);
//		d1.setSn("1111");
//		d1.setName("111111");
//		list.add(d1);
//		DTeacher d2=new DTeacher();
//		d2.setId(2);
//		d2.setSn("2222");
//		d2.setName("222222");
//		list.add(d2);

		return null;
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
		return null;
	}
	
	 /* 列出某学院某学期所有课程和负责人信息
	  * 参数term
	  * 返回值json
	  * */
	@RequestMapping("/termcoursemaster")
	@ResponseBody
	public List<TermCourseMaster> getTermCourseMaster(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		return null;
	}
	
	 /* 新增某学院课程（成功会生成courseid，批量添加入口稍后添加）
	  * 参数number,name（collegeId学院id从后台取）
	  * 返回值true,false
	  * */
	@RequestMapping("/addcourse")
	@ResponseBody
	public boolean addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String number= request.getParameter("number");
		String name= request.getParameter("name");
		return true;
	}
	
	 /* 删除某学院课程
	  * 参数courseid（collegeId学院id从后台读取）
	  * 返回值true,false
	  * */
	@RequestMapping("/deletecourse")
	@ResponseBody
	public boolean deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String courseid= request.getParameter("courseid");
		return true;
	}
	 /* 设置某学期下，（某学院）某课程的课程负责人
	  * 参数term, courseid, mastersn（collegeId学院id从后台取）
	  * 返回值true,false
	  * */
	@RequestMapping("/setcoursemaster")
	@ResponseBody
	public boolean setCourseMaster(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		String mastersn= request.getParameter("mastersn");
		return true;
	}
	 /* 设置某课由谁来教 或 设置某老师教什么课,并设置这门课这位老师下面的班级数
	  * 参数term, courseid, teachersn, classnumber（collegeId学院id从后台取, classnumber为-1则不限制）
	  * 返回值true,false
	  * */
	@RequestMapping("/setcourseteacher")
	@ResponseBody
	public boolean getCourseTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		String teachersn= request.getParameter("teachersn");
		String classnumber= request.getParameter("classnumber");
		return true;
	}
	
	 /* 获取某课由谁来教，有哪些班级
	  * 参数term, courseid（collegeId学院id从后台取, classnumber为-1则不限制）
	  * 返回值
	  * */
	@RequestMapping("/getcourseteacher")
	@ResponseBody
	public TermCourseMaster getcourseteacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String term= request.getParameter("term");
		String courseid= request.getParameter("courseid");
		return null;
	}
	
}
