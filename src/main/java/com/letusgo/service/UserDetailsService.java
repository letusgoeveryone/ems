package com.letusgo.service;

import java.util.ArrayList;

import org.hibernate.Transaction;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.StudentDaoImp;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.model.Student;
import com.letusgo.model.Teacher;


public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=null;
		String password=null; 
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		try {
			StudentDaoImp studentDaoImp= new StudentDaoImp();
			Student student=studentDaoImp.getStudentBySn(username);
			password=student.getPassword();
			
			System.out.println(student);
		} catch (Exception e) {
			try {
				TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
				Teacher teacher=teacherDaoImp.getTeacherBySn(username);
				System.out.println(teacher);
				password=teacher.getPassword();
				System.out.println(teacher);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		beginTransaction.commit();//业务结尾   

	    //欲比对的md5统一大小写
	    password=password.toLowerCase();
	    //账户是否可用
	    boolean enabled = true;
	    //账户是否没过期
	    boolean accountNonExpired = true;
	    //凭证是否没有过期
	    boolean credentialsNonExpired = true;
	    //账号是否没有被锁定
	    boolean accountNonLocked = true;
	    user=new User(username, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, getAuthoritiesBySn(username));
	    return user;
        
	}
	
	public static ArrayList<GrantedAuthority> getAuthoritiesBySn(String sn){
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		String str[] = {"ROLE_TEACHER","ROLE_ACDEMICDEAN","ROLE_ADMIN","ROLE_STUDENT"};
		Transaction beginTransaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();//业务开头
		try {
			StudentDaoImp studentDaoImp= new StudentDaoImp();
			Student student=studentDaoImp.getStudentBySn(sn);
			student.getName();
			authorities.add(new SimpleGrantedAuthority(str[3]));  
		} catch (Exception e) {
			try {
				TeacherDaoImp teacherDaoImp= new TeacherDaoImp();
				Teacher teacher=teacherDaoImp.getTeacherBySn(sn);
				int roleValue;
				try {
					roleValue = Integer.valueOf(teacher.getRoleid());
					if (roleValue<=0 ||roleValue>7) {
						return null;
					}
					char[] ch= Integer.toBinaryString(roleValue).toCharArray();
		            int j=0;
		            for (int i = ch.length-1; i >=0; i--) {
		                if(String.valueOf(ch[i]).equals("1")){
		                authorities.add(new SimpleGrantedAuthority(str[j]));
		                j++;
		                }
		            }
				} catch (NumberFormatException e1) {
					authorities.add(new SimpleGrantedAuthority(str[0]));
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		beginTransaction.commit();//业务结尾   
		return authorities;
	}
	  

}
