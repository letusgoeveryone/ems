package junitTest;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.format.datetime.standard.TemporalAccessorParser;

import com.fasterxml.jackson.core.sym.Name;
import com.letusgo.HibernateUtil.HibernateUtil;
import com.letusgo.daoImp.TeacherDaoImp;
import com.letusgo.dto.DTeacher;
import com.letusgo.dto.DTermCourseMaster;
import com.letusgo.model.Teacher;
import com.letusgo.model.Termcourse;
import com.letusgo.service.AcdemicDeanService;

import jdk.nashorn.internal.ir.annotations.Ignore;
import sun.net.TransferProtocolClient;

public class CourseDaoTest {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		new TeacherDaoImp().setTermCourse( 1,3, "2016-02");
		transaction.commit();
		
	}
	
//	public void name() {
//		List<DTeacher> list = new AcdemicDeanService().GetAllTeacher();
//		System.out.println("size      *****"+list.size());
//		for(DTeacher teacher : list){
//			System.out.println(teacher);
//		}
//	}

	
	@Ignore
	@Test
	public void test() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String term = "2016-01";
		int id = 1;
		
		Query query = session.createQuery("FROM Termcourse t WHERE t.term = :term and t.courseid.college.id = :collegeId");
		query.setString("term", term);
		query.setInteger("collegeId", id);
		List<Termcourse> list = query.list();
		for(Termcourse termcourse : list){
			DTermCourseMaster temp = new DTermCourseMaster();
			temp.setCourseName(termcourse.getCourse().getName());
			temp.setNumber(termcourse.getCourse().getNumber());
			temp.setSn(termcourse.getTeacher().getSn());
			temp.setTeaName(termcourse.getTeacher().getName());
			System.out.println(temp);
		}
		
		
		transaction.commit();
	}

}
