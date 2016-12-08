package com.letusgo.controller;

import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.CollegeDaoImp;
import com.letusgo.model.College;


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
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		//SQLQuery createSQLQuery = HibernateUtil.getSessionFactory().getCurrentSession().createSQLQuery("INSERT INTO college(currterm,name) VALUES('201702','文学院'); "); //hibernate SQLQuery
		College college=new College("文学院");
		college.setCurrterm("201702");
		CollegeDaoImp collegeDaoImp=new CollegeDaoImp();
		collegeDaoImp.addCollege(college);
		beginTransaction.commit();//业务结尾
		return "index";
	}
}
