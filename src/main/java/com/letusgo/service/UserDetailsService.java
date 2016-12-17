package com.letusgo.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=null;
	    String password = "e10adc3949ba59abbe56e057f20f883e"; 
	    /*
	     * 在这里填充正确的password,去数据库里取
	     * */
	    
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
		String str[] = {"ROLE_DEAN","ROLE_TEACHER","ROLE_STUDENT","ROLE_ADMIN"};
		authorities.add(new SimpleGrantedAuthority("ROLE_ACDEMICDEAN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));    
		return authorities;
	}
	  

}
