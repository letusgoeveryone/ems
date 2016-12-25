package com.letusgo.dto;

public class DCourse {
    private Integer id;
    private String number;
    private String coursename;
    private String collegename;
    private String collegeid;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public DCourse(Integer id, String number, String coursename, String collegename, String collegeid) {
		super();
		this.id = id;
		this.number = number;
		this.coursename = coursename;
		this.collegename = collegename;
		this.collegeid = collegeid;
	}
	public DCourse() {
		super();
	}
	@Override
	public String toString() {
		return "DCourse [id=" + id + ", number=" + number + ", coursename=" + coursename + ", collegename="
				+ collegename + ", collegeid=" + collegeid + "]";
	}
    
}
