package com.letusgo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letusgo.dto.DCollege;
import com.letusgo.dto.DTeacher;
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
	 /* 列出所有学院
	  * 返回值
	  * */
	@RequestMapping("/getallcollege")
	@ResponseBody
	public List<DCollege> getAllTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AdminService adminService= new AdminService();
		return adminService.getAllCollege();
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
		String collegeid= request.getParameter("collegeid");
		AdminService adminService= new AdminService();
		return adminService.addTeacher(sn, name, password,Integer.valueOf(collegeid));
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
}
