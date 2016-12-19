/**2016年12月19日
 * 
 */
package com.letusgo.service;

import org.hibernate.Query;

import com.letusgo.model.Termteacher;

/** 
* @author  lbx E-mail:1274604226@qq.com 
* @version createdata：2016年12月19日 下午8:13:16 
* description:
*/

public class ITeacherServerImpl implements ITeacherServer{
	public void SetIntroduce(int TermCourseId, int TeacherId,String introduce) {
        Query query = getSession().createQuery("FROM Termteacher t where t.teacher.id = :tid and t.termcourse.id = :cid");
        query.setInteger("tid", TeacherId);
        query.setInteger("cid", TermCourseId);
        Termteacher termteacher = (Termteacher) query.uniqueResult();
        termteacher.getTermcourse().getCourse().setIntroduce(introduce);
    }

    public void SetSyllabus(int TermCourseId, int TeacherId,String syllabus) {
        Query query = getSession().createQuery("FROM Termteacher t where t.teacher.id = :tid and t.termcourse.id = :cid");
        query.setInteger("tid", TeacherId);
        query.setInteger("cid", TermCourseId);
        Termteacher termteacher = (Termteacher) query.uniqueResult();
        termteacher.getTermcourse().getCourse().setIntroduce(syllabus);
    }
}
