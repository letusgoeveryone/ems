package com.letusgo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letusgo.dto.DCollege;
import com.letusgo.dto.DTeacher;
import com.letusgo.dto.DTermCourseMaster;
import com.letusgo.service.AcdemicDeanService;
import com.letusgo.service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	/***
	 * 响应admin页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "admin";
	}
	 /** 列出所有学院
	  * 返回值
	  * */
	@RequestMapping("/getallcollege")
	@ResponseBody
	public List<DCollege> getCollege(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AdminService adminService= new AdminService();
		return adminService.getAllCollege();
	}
	
	/** 新增教师
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
		String collegeid= request.getParameter("collegeid");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.addTeacher(sn, name, password,Integer.valueOf(collegeid));
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
	
	 /**修改教师
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
	
	 /** 列出所有教师
	  * 返回值
	  * */
	@RequestMapping("/getallteacher")
	@ResponseBody
	public List<DTeacher> getAllTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AdminService adminService= new AdminService();
		return adminService.getAllTeacher();
	}
	
	 /** 通过工号读取教师信息
	  * 参数sn
	  * 返回值json
	  * */
	@RequestMapping("/findateacher")
	@ResponseBody
	public DTeacher findATeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AdminService adminService= new AdminService();
		return adminService.findateacher(sn);
	}
	
	 /** 设置教务管理员
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/setacdemicdean")
	@ResponseBody
	public String setAcdemicDean(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AdminService adminService= new AdminService();
		return adminService.setAcdemicDean(sn);
	}
	 /** 设置教务管理员
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/removeacdemicdean")
	@ResponseBody
	public String removeAcdemicDean(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AdminService adminService= new AdminService();
		return adminService.removeAcdemicDean(sn);
	}
	
	 /** 设置教务管理员
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/setadmin")
	@ResponseBody
	public String setAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AdminService adminService= new AdminService();
		return adminService.setAcdemicDean(sn);
	}
	 /** 设置教务管理员
	  * 获取参数sn
	  * 返回值true,false
	  * */
	@RequestMapping("/removeadmin")
	@ResponseBody
	public String removeAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String sn= request.getParameter("sn");
		AdminService adminService= new AdminService();
		return adminService.removeAcdemicDean(sn);
	}
	
	 /**获取某学院教师信息
	  * 获取参数collegeid学院id
	  * 返回值true,false
	  * */
	@RequestMapping("/getcollegeteacher")
	@ResponseBody
	public List<DTeacher> getCollegeTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		int collegeid= Integer.valueOf(request.getParameter("collegeid"));
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.getAllTeacher(collegeid);
	}
	 /**获取某学院课程信息
	  * 获取参数collegeid学院id
	  * 返回值true,false
	  * */
	@RequestMapping("/getcollegecourse")
	@ResponseBody
	public List<DTermCourseMaster> getCollegeCourse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		int collegeid= Integer.valueOf(request.getParameter("collegeid"));
		String term=request.getParameter("term");
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		return acdemicDeanService.GetAllCourse(collegeid, term);
	}
}
