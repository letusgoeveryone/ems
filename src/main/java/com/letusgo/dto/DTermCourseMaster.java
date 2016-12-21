package com.letusgo.dto;

public class DTermCourseMaster {
	private int id;
	private String number;
	private String courseName;
	private String sn;
	private String teaName;
	

	public DTermCourseMaster(String num) {
		this.number = num;
	}
	
	
	public DTermCourseMaster() {
		super();
	}

	public String getNumber() {
		return number;
	}
	public DTermCourseMaster(String number, String courseName, String sn, String teaName) {
		super();
		this.number = number;
		this.courseName = courseName;
		this.sn = sn;
		this.teaName = teaName;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public DTermCourseMaster(int id, String number, String courseName, String sn, String teaName) {
		super();
		this.id = id;
		this.number = number;
		this.courseName = courseName;
		this.sn = sn;
		this.teaName = teaName;
	}


	@Override
	public String toString() {
		return "DTermCourseMaster [id=" + id + ", number=" + number + ", courseName=" + courseName + ", sn=" + sn
				+ ", teaName=" + teaName + "]";
	}



	

}
