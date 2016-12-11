package com.letusgo.dto;

public class TermCourseMaster {
	private String number;
	private String courseName;
	private String sn;
	private String teaName;
	
	public TermCourseMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public TermCourseMaster(String num) {
		this.number = num;
	}
	
	
	
	public String getNumber() {
		return number;
	}
	public TermCourseMaster(String number, String courseName, String sn, String teaName) {
		super();
		this.number = number;
		this.courseName = courseName;
		this.sn = sn;
		this.teaName = teaName;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	@Override
	public String toString() {
		return "TermCourseMaster [number=" + number + ", courseName=" + courseName + ", sn=" + sn + ", teaName="
				+ teaName + "]";
	}
	
	

}
