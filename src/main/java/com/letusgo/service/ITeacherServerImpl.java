/**2016年12月19日
 * 
 */
package com.letusgo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.model.Selectcourse;
import com.letusgo.model.Student;
import com.letusgo.model.Termteacher;


/** 
* @author  lbx E-mail:1274604226@qq.com 
* @version createdata：2016年12月19日 下午8:13:16 
* description:
*/

public class ITeacherServerImpl implements ITeacherServer{
	public Boolean setIntroduce(int TermCourseId, int TeacherId,String introduce) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Termteacher t where t.teacher.id = :tid and t.termcourse.id = :cid");
        query.setInteger("tid", TeacherId);
        query.setInteger("cid", TermCourseId);
        Termteacher termteacher = (Termteacher) query.uniqueResult();
        termteacher.getTermcourse().getCourse().setIntroduce(introduce);
        transaction.commit();
		return true;
    }

    public  Boolean setSyllabus(int TermCourseId, int TeacherId,String syllabus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Termteacher t where t.teacher.id = :tid and t.termcourse.id = :cid");
        query.setInteger("tid", TeacherId);
        query.setInteger("cid", TermCourseId);
        Termteacher termteacher = (Termteacher) query.uniqueResult();
        termteacher.getTermcourse().getCourse().setIntroduce(syllabus);
        transaction.commit();
		return true;
    }
    
    /**
	 * 查询本班学生
	 * @param termCourseId 学期课程id
	 * @param teaId  教师id
	 * @return  学生集合
	 */
	public List getStudent(int termCourseId,int teaId){
		List <Student> list = new ArrayList<Student>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Termteacher termTacher = (Termteacher) session.createQuery("from Termteacher where TermCourse_id = ? and Teacher_id = ?")
				.setParameter(0, termCourseId)
				.setParameter(1, teaId).list().get(0);
		int TermTeacherId = termTacher.getId();
		List <Selectcourse> selectCoursrList = session.createQuery("from Selectcourse where Termclass_id= (from Termclass where Termteacher_id = ?)")
				.setParameter(0, TermTeacherId).list();
		Iterator it =  selectCoursrList.iterator();
		while(it.hasNext()){
			Selectcourse s = (Selectcourse)it.next();
			Student stu = (Student) session.createQuery("from Student where id = :ID").setParameter("ID", s.getStudentid());
			list.add(stu);
		}
		return list;
	}
    
	/* (non-Javadoc)
	 * @see com.letusgo.service.ITeacherServer#addClass(int, int)
	 */
	@Override
	public Boolean addClass(int teacherId, int classId) {
		
		return null;
	}
}
