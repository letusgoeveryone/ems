/**2016年12月19日
 * 
 */
package com.letusgo.service;
/** 
* @author  lbx E-mail:1274604226@qq.com 
* @version createdata：2016年12月19日 下午8:50:15 
* description:
*/

public interface ITeacherServer {
	/**
	 * 
	
	* @Description: 添加课程介绍
	
	* @param  TermCourseId 学期课程id
	* @param  TeacherId  教师id
	* @param  introduce    课程介绍
	
	* @return void
	 */
	public Boolean setIntroduce(int TermCourseId, int TeacherId,String introduce) ;
	
	/**
	 * 
	
	* @Description: 添加课程大纲
	
	* @param  TermCourseId 学期课程id
	* @param  TeacherId  教师id
	* @param  syllabus     课程大纲
	
	* @return void
	 */
	public Boolean setSyllabus(int TermCourseId, int TeacherId,String syllabus);
	
	/**
	 * 
	
	* @Description: 添加班级
	
	* @param  teacherId
	* @param  classId
	* @param     
	
	* @return Boolean
	 */
	public Boolean addClass(int teacherId,int classId);
	
}
