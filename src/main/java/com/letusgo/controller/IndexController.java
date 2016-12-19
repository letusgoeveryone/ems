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
import com.letusgo.dto.DCollege;
import com.letusgo.model.College;
import com.letusgo.service.AdminService;


@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/")
	public String index1(){
		System.out.println(getMD5("123456"));
		return "index";
	}
	@RequestMapping("/test_insert_a_college")
	public String testInsertACollege(){//sql小例子
		AdminService adminService= new AdminService();
		adminService.addCollege("软件学院", "2016-01");
		List<DCollege> list=adminService.getAllCollege();
		for (DCollege dCollege : list) {
			if (dCollege.getName().equals("软件学院") ) {
				adminService.addTeacher("10250007", "王红涛", getMD5("123456"), dCollege.getId());
			}
		}
		return "index";
	}
    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            
        }
        return "";
    }
}
