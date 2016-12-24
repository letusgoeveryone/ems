package junitTest;

import org.junit.Test;

import com.letusgo.service.StudentService;

public class StudentServiceTest {
	StudentService ss=new StudentService();
	
	@Test
	public void testGetTeaByScid(){
		ss.getTeaByScid("2");
		
	}

}
