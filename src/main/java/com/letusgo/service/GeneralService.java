package com.letusgo.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.StudentDaoImp;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.model.Student;
import com.letusgo.model.Teacher;

public class GeneralService {
//	public static void main(String[] args) {
//		FileSystemXmlApplicationContext cpxa = new  FileSystemXmlApplicationContext("D:/Program Files/JavaWeb/EclipseJeeProjects/ems/src/main/webapp/WEB-INF/springmvc-servlet.xml");
//		Object object = cpxa.getBean("myid");
//		if (object == null) {
//			System.out.println("kong");
//		}else {
//			System.out.println("bukong");
//		}
//	}
//	
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
    /***
     * 获取当前用户sn
     * @return
     */
    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
     }
    /***
     * 获取当前用户所在学院id
     * @return
     */
    public int getCurrentUserCollegeid() {
    	Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		try {
			TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
			Teacher teacher=teacherDaoImp.getTeacherBySn(SecurityContextHolder.getContext().getAuthentication().getName());
			beginTransaction.commit();//业务结尾 
			return teacher.getCollege().getId();
		} catch (Exception e) {
			try {
				StudentDaoImp studentDaoImp= new StudentDaoImp();
				Student student=studentDaoImp.getStudentBySn(SecurityContextHolder.getContext().getAuthentication().getName());
				beginTransaction.commit();//业务结尾 
				return student.getCollege().getId();
			} catch (Exception e1) {
			}finally {
				beginTransaction.commit();//业务结尾   
			}
		}
		   return 0;
     }
}
