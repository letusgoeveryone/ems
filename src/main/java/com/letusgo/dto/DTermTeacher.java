package com.letusgo.dto;

public class DTermTeacher
{
  private int termTeacherid;
  private String sn;
  private String teacherName;
  private int maxclass;

  public DTermTeacher(int termTeacherid, String sn, String teacherName, int maxclass)
  {
    this.termTeacherid = termTeacherid;
    this.sn = sn;
    this.teacherName = teacherName;
    this.maxclass = maxclass;
  }

  public String toString() {
    return "DTermTeacher [termTeacherid=" + this.termTeacherid + ", sn=" + this.sn + ", teacherName=" + this.teacherName + 
      ", maxclass=" + this.maxclass + "]"; }

  public int gettermTeacherid() {
    return this.termTeacherid; }

  public void settermTeacherid(int termTeacherid) {
    this.termTeacherid = termTeacherid; }

  public String getsn() {
    return this.sn; }

  public void setsn(String sn) {
    this.sn = sn; }

  public String getteacherName() {
    return this.teacherName; }

  public void setteacherName(String teacherName) {
    this.teacherName = teacherName; }

  public int getMaxclass() {
    return this.maxclass; }

  public void setMaxclass(int maxclass) {
    this.maxclass = maxclass;
  }
}