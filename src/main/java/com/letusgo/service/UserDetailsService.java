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


	    /*
	     * 在这里填充正确的password,去数据库里取
	     * */
	    

		String password=null; 
//	    String password = "e10adc3949ba59abbe56e057f20f883e"; 
	    /*
	     * 在这里填充正确的password,去数据库里取
	     * */
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

		String str[] = {"ROLE_ACDEMICDEAN","ROLE_TEACHER","ROLE_STUDENT","ROLE_ADMIN"};
		for (int i = 0; i < str.length; i++) {
			authorities.add(new SimpleGrantedAuthority(str[i]));  
		}

		return authorities;
	}
	  

}
