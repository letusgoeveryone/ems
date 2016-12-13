package com.letusgo.dto;

public class DTeacherClass {
	private String teacherSn;
	private String teacherName;
	private String ClassName;
	public DTeacherClass(String teacherSn, String teacherName, String className, String classId) {
		super();
		this.teacherSn = teacherSn;
		this.teacherName = teacherName;
		ClassName = className;
		ClassId = classId;
	}
	
	public DTeacherClass() {
		super();
	}

	public String getTeacherSn() {
		return teacherSn;
	}
	public void setTeacherSn(String teacherSn) {
		this.teacherSn = teacherSn;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getClassId() {
		return ClassId;
	}
	public void setClassId(String classId) {
		ClassId = classId;
	}
	private String ClassId;
	
}
