package com.letusgo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.letusgo.model.Student;
import com.letusgo.service.GeneralService;
import com.letusgo.service.StudentService;

@Controller
@RequestMapping("/student/")
public class StudentController {

	// 学生主页
	@RequestMapping("/")
	public String stuIndex() {
		return "student/student";

	}

	// 显示个人信息
	@RequestMapping("/info")
	public String showStuInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		GeneralService generalService = new GeneralService();
		String sn = generalService.getCurrentUsername();
		StudentService studentService = new StudentService();
		student = studentService.getStudentInfo(sn);
		// String name = request.getParameter("name");
		System.out.println(student);
		request.setAttribute("name", student.getName());
		if (student.getSex() == true) {
			request.setAttribute("sex", "男");
		} else {
			request.setAttribute("sex", "女");
		}

		request.setAttribute("qq", student.getQq());
		request.setAttribute("tel", student.getTel());
		request.setAttribute("email", student.getEmail());
		// request.getRequestDispatcher("stuinfo").forward(request, response);

		return "student/stuinfo";
	}

	// 修改个人信息
	@RequestMapping(value = "/modifyinfo", method = RequestMethod.POST)
	@ResponseBody
	public String modifyStuInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// request.getRequestDispatcher("stuinfo").forward(request, response);
		Student student_new = new Student();
		Student student_old = new Student();
		GeneralService generalService = new GeneralService();
		StudentService studentService = new StudentService();
		student_old = studentService.getStudentInfo(generalService.getCurrentUsername());
		// System.out.println(request.getParameter("test"));
		student_new.setSn(generalService.getCurrentUsername());
		student_new.setName(request.getParameter("name"));
		if (request.getParameter("sex") == "男") {
			student_new.setSex(true);
		} else {
			student_new.setSex(false);
		}
		student_new.setPassword(student_old.getPassword());
		// student.setName(request.getParameter("sex"));
		student_new.setQq(request.getParameter("qq"));
		student_new.setTel(request.getParameter("tel"));
		student_new.setEmail(request.getParameter("email"));
		studentService.modifyInfoBySn(student_new);
		System.out.println(student_new);
		return "Modify Success !";

	}

	// 转到重置密码页面
	@RequestMapping("/topwdreset")
	public String toPwdReset() {
		return "student/pwdreset";
	}

	// 密码重置
	@RequestMapping("/pwdreset")
	@ResponseBody
	public String pwdReset(HttpServletRequest request, HttpServletResponse response) {
		StudentService studentService = new StudentService();
		GeneralService generalService = new GeneralService();

		String pwd_MD5;
		String newPwd = request.getParameter("new");
		String renewPwd = request.getParameter("renew");

		if (newPwd.equals(renewPwd)) {
			System.out.println(newPwd + "-----" + renewPwd);
			pwd_MD5 = generalService.getMD5(newPwd);
			studentService.modifyPwdBySnAndPwd(generalService.getCurrentUsername(), pwd_MD5, pwd_MD5);
			return "Modify Success !";
		} else {
			return "Modify Fail !";
		}

	}

	// 转到作业提交页面
	@RequestMapping("/toHomework")
	public String toHomework() {
		return "student/homework";

	}

	// 学生提交作业
	@RequestMapping("/uploadHW")
	@ResponseBody
	public String uploadHW(MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		//String path=request.getContextPath();
		//String path="../upload";
		String fileName = file.getOriginalFilename();
		System.out.println(path+"----"+fileName);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
		// String fileName=File
		return "Upload Success !";

	}

}
