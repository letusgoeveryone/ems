package com.letusgo.dto;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.letusgo.model.College;
import com.letusgo.model.Termcourse;

public class DTeacher {
    private Integer id;
    private String sn;
    private String name;
    private String password;
    private Boolean sex;
    private String avatarid;
    private String tel;
    private String qq;
    private String email;
    private Date regdate;
    private String roleid;
    private int collegeid;
    
    public DTeacher() {
		// TODO Auto-generated constructor stub
	}
    
    
    
	public DTeacher(Integer id, String sn, String name, String password, Boolean sex, String avatarid, String tel,
			String qq, String email, Date regdate, String roleid, int collegeid) {
		super();
		this.id = id;
		this.sn = sn;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.avatarid = avatarid;
		this.tel = tel;
		this.qq = qq;
		this.email = email;
		this.regdate = regdate;
		this.roleid = roleid;
		this.collegeid = collegeid;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getAvatarid() {
		return avatarid;
	}
	public void setAvatarid(String avatarid) {
		this.avatarid = avatarid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public int getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(int collegeid) {
		this.collegeid = collegeid;
	}
	@Override
	public String toString() {
		return "DTeacher [id=" + id + ", sn=" + sn + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", avatarid=" + avatarid + ", tel=" + tel + ", qq=" + qq + ", email=" + email + ", regdate=" + regdate
				+ ", roleid=" + roleid + ", collegeid=" + collegeid + "]";
	}
    
}
