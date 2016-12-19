package com.letusgo.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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
	public List<String> getAllTerm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Calendar calendar=Calendar.getInstance();
		List<String> allterm=new ArrayList<String>();
		allterm.add(calendar.get(Calendar.YEAR)-2+"-01");
		allterm.add(calendar.get(Calendar.YEAR)-2+"-02");
		allterm.add(calendar.get(Calendar.YEAR)-2+"-03");
		allterm.add(calendar.get(Calendar.YEAR)-1+"-01");
		allterm.add(calendar.get(Calendar.YEAR)-1+"-02");
		allterm.add(calendar.get(Calendar.YEAR)-1+"-03");
		allterm.add(calendar.get(Calendar.YEAR)+"-01");
		allterm.add(calendar.get(Calendar.YEAR)+"-02");
		allterm.add(calendar.get(Calendar.YEAR)+"-03");	
		allterm.add(calendar.get(Calendar.YEAR)+1+"-01");
		allterm.add(calendar.get(Calendar.YEAR)+1+"-02");
		allterm.add(calendar.get(Calendar.YEAR)+1+"-03");			
		return allterm;
	}
	
	@RequestMapping("/getcurrentterm")
	@ResponseBody
	public String getCurrentTerm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "2016-01";
	}
}
