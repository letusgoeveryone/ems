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
	
	* @param @param TermCourseId 学期课程id
	* @param @param TeacherId  教师id
	* @param @param introduce    课程介绍
	
	* @return void
	 */
	public void SetIntroduce(int TermCourseId, int TeacherId,String introduce) ;
	
	/**
	 * 
	
	* @Description: 添加课程大纲
	
	* @param @param TermCourseId 学期课程id
	* @param @param TeacherId  教师id
	* @param @param syllabus     课程大纲
	
	* @return void
	 */
	public void SetSyllabus(int TermCourseId, int TeacherId,String syllabus);
}
