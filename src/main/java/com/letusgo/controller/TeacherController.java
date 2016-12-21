/**2016年12月20日
 * 
 */
package com.letusgo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letusgo.service.ITeacherServerImpl;

/** 
* @author  lbx E-mail:1274604226@qq.com 
* @version createdata：2016年12月20日 下午5:58:56 
* description:
*/

@Controller
@RequestMapping("/teacher/")
public class TeacherController {
	
	/**
	 * 
	
	* @Description: 添加课程介绍
	
	* @param  TermCourseId 学期id
	* @param  TeacherId  教师id
	
	* @return String 1表示成功添加课程介绍  0表示添加课程介绍失败
	 */
	@RequestMapping("/setintroduce")
	public @ResponseBody String setIntroduce(HttpServletRequest request,HttpServletResponse response){
		int TermCourseId = Integer.parseInt(request.getParameter("TermCourseId"));
		int TeacherId = Integer.parseInt(request.getParameter("TeacherId"));
		String introduce = request.getParameter("introduce");
		Boolean a =  new ITeacherServerImpl().setIntroduce(TermCourseId, TeacherId, introduce);
		if(a)
			return "1";
		return "0";
	}
	
	
	/**
	 * 
	
	* @Description: 添加课程大纲
	
	* @param  TermCourseId 学期id
	* @param  TeacherId  教师id
	* @return String 1表示成功添加课程大纲  0表示添加课程大纲失败
	 */
	@RequestMapping("/setsyllabus")
	public @ResponseBody String setSyllabus(HttpServletRequest request,HttpServletResponse response){
		int TermCourseId = Integer.parseInt(request.getParameter("TermCourseId"));
		int TeacherId = Integer.parseInt(request.getParameter("TeacherId"));
		String syllabus = request.getParameter("syllabus");
		Boolean a =  new ITeacherServerImpl().setSyllabus(TermCourseId, TeacherId, syllabus);
		if(a)
			return "1";
		return "0";
	}
	
	/**
	 * 
	
	*  @Description: 添加班级
	
	* @param request
	* @param response
	* @return    
	
	* @return String
	 */
	@RequestMapping("/addclass")
	public @ResponseBody String addClass(HttpServletRequest request,HttpServletResponse response){
		
		return null;
	}
	

}
