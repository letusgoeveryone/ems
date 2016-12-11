package com.letusgo.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class GeneralService {
	public static void main(String[] args) {
		FileSystemXmlApplicationContext cpxa = new  FileSystemXmlApplicationContext("D:/Program Files/JavaWeb/EclipseJeeProjects/ems/src/main/webapp/WEB-INF/springmvc-servlet.xml");
		Object object = cpxa.getBean("myid");
		if (object == null) {
			System.out.println("kong");
		}else {
			System.out.println("bukong");
		}
	}
}
