package com.letusgo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 通用controller
 * 作用：提取出来各个角色可公用的Controller
 * 比如
 * */
@Controller
@RequestMapping("/")
public class GeneralController {
	@RequestMapping("/getallterm")
	@ResponseBody
	public String[] getAllTerm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] aa={"201501","201601"};
		return aa;
	}
	
	@RequestMapping("/getcurrentterm")
	@ResponseBody
	public String getCurrentTerm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "201601";
	}
}
