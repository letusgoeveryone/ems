package com.letusgo.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DCollege;
import com.letusgo.model.College;
import com.letusgo.model.Teacher;
import com.letusgo.service.AcdemicDeanService;
import com.letusgo.service.AdminService;
import com.letusgo.service.GeneralService;


@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/")
	public String index1(){	
		return "index";
	}
	@RequestMapping("/test_insert_a_college")
	public String testInsertACollege(){//sql小例子
		AdminService adminService= new AdminService();
		AcdemicDeanService acdemicDeanService= new AcdemicDeanService();
		adminService.addCollege("软件学院", "2016-01");
		List<DCollege> list=adminService.getAllCollege();
		for (DCollege dCollege : list) {
			if (dCollege.getName().equals("软件学院") ) {
				acdemicDeanService.addTeacher("10250007", "红涛", (new GeneralService()).getMD5("123456"), dCollege.getId());
				adminService.setAcdemicDean("10250007");
				adminService.setAdmin("10250007");
			
			}
		}
		return "index";
	}

}
