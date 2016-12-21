/**2016年12月19日
 * 
 */
package com.letusgo.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.letusgo.HibernateUtil.HibernateUtil;

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

    public Boolean setSyllabus(int TermCourseId, int TeacherId,String syllabus) {
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

	/* (non-Javadoc)
	 * @see com.letusgo.service.ITeacherServer#addClass(int, int)
	 */
	@Override
	public Boolean addClass(int teacherId, int classId) {
		
		return null;
	}
}
