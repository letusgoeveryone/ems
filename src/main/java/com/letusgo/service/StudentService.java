package com.letusgo.service;

import java.util.List;

import com.letusgo.dao.StudentDao;
import com.letusgo.dao.TeacherDao;
import com.letusgo.model.Student;

public class StudentService{
	 /**
     * 学生登录
     * @param userName 用户名，填写学号（10位），手机号（11位），身份证号（其它位数）都可以
     * @param stuPwd   密码
     * @return  用户名正确，不重复，且与相应的密码吻合则返回true，否则返回false
     */
    //根据学号，密码登录认证
	 public static boolean studentSignInByUserName(String userName, String stuPwd){
	 	Student student = StudentDao.getStudentBySn(userName);
	 	if(userName.equals(student.getStudentName())){
	 		return true;
	 	}
	 	return false;
	 }

	 	 /**
     * 学生密码找回？
     * @param userName 用户名，填写学号（10位），手机号（11位），身份证号（其它位数）都可以
     *
     * @return  用户名正确，不重复，且与相应的密码吻合则返回true，否则返回false
     */

	 	 public static String studentLoginGetPasswordByUserName(String userName){
	 	 	Student student = StudentDao.getStudentBySn(userName);
	 	 	if (student != null) {
	 	 		return student.getStudentPwd();
	 	 	}else{
	 	 		return "";
	 	 	}
	 	 }
	     /**
     *为所有学生分页
     * @param pc 当前页
     * @param ps 每页记录数
     * @return 返回一个分页bean对象
     */
	     public static PageBean<Student>  findAll(Integer pc, Integer ps){
	     	return StudentDao.findAll(pc, ps);
	     }

	         /**
     * 将一个临时学生加入到正式学生中
     *
     * @param tempStudent 临时学生对象
     */
	         public static void addStudentFromtempStudent(TempStudent tempStudent) {
	         	StudentDao.addStudentFromtempStudent(tempStudent);
	         }

        /**
     * 获取全部正式学生
     * @return 返回全体正式学生的集合（不含密码）
     */
        public List getAllStudent() {
        	return StudentDao.getAllStudent();
        }
        /**
     * 根据id将一个临时学生加入到正式学生中
     * @param id 临时学生id
     */
        public void addStudentFromtempStudent(Integer id) {
        	StudentDao.addStudentFromtempStudent(TempStudentDao.getTempStudentById(id));
        }

     /**
     * 根据学号获取一个学生的信息
     * @param  学生学号sn
     */
     public void getStudentInfo(String sn){
     	Student std=StudentDao.getStudentBySn(sn);
     	return std;
     }

   	 /**
     * 密码修改
     * @param  学生学号sn
     * @param  第一次输入的密码
     * @param  第二次输入的密码
     */
   	 public void modifyPwdBySnAndPwd(String sn,String pw,String repw){
   	 	Student stu=new Student();
   	 	stu=getStudentInfo(sn);

   	 	std.setStudentPwd(repw);
   	    StudentDao.updateStudent(std);

   	 }

   	 /**
     * 个人信息的修改
     * @param  学生学号sn
     * @param  第一次输入的密码
     * @param  第二次输入的密码
     */
     public void modifyPwdBySnAndPwd(String sn,String name,String StudentGrade,String StudentCollege,String StudentSex,String StudentTel,String StudentQq)
     {
     	Student std=StudentDao.getStudentBySn(sn);
     	std.setStudentName(name);
     	std.setStudentGrade(Integer.valueOf(grade));
        std.setStudentCollege(college);
        std.setStudentSex(sex.equals("男"));
        std.setStudentTel(telnum);
        std.setStudentQq(qqnum);
     	Student.updateStudent(std);
     }

     /**
     * 学生头像ID修改的修改
     * @param  学生学号sn
     * @param  当前头像ID
     *
     */
     public void ModifyStuAvatarId(String sn,String imgid){
			 Student std=StudentDao.getStudentBySn(sn);
			 std.setStudentImg(imgid);
			 StudentDao.updateStudent(std);
		 }

		 /**
		 * 返回课程页详情
		 * @param  课程号scid
		 *
		 */
		 public Teacher getTeaByScid(String scid){
			 String TecSn=TermCourseDao.getTecsnByCourseId(scid);
			 Teacher tec=TeacherDao.getTeacherById(TecSn);
			 return tec;
		}
		/**
		* 通过课程号返回学期
		* @param  课程号scid
		*
		*/
		public String getTermByScid(String scid){
			String term=TermCourseDao.getxueqiBySCId(scid).toString();
			return term;
		}

		/**
		* 通过课程名称返回学期
		* @param  课程号scid
		*
		*/
		public String getCourseNameByScid(String scid){
		 String courseName=TermCourseDao.getCourseNameByCourseId(scid);
		 return courseName;

		}

		/**
		* 通过学期号和学号来获取学生选的课
		* @param  学期号
		* @param  学号
		*/
		public List getStuSelectedCourseByTermIdAndCourseId(String term,String stusn)
		{
			List list=StudentSelectCourseDao.getStudentSelectCourseNameByTermSnCourseId2(term, stusn);
			return list;
		}
		//

}
