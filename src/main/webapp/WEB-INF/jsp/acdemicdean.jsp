<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教务管理员</title>
<script src="../js/jquery.js"></script>
<script src="../js/md5.js"></script>
</head>
<body>
	
	<h2>教务管理员</h2>
	<a href="../">首页</a>|<a href="../logout">登出</a><br>
	=================================================<br>
	<div>
	<h4>新增本学院教师</h4>
	工号：<input type="text" id="username" value="10250007"><br>
	姓名：<input type="text" id="teachername" value="王红涛"><br>
	密码：<input type="text" id="password" value="123456">
	<button id="addteacher">新增</button>
	</div>
	=================================================<br>
	<div>
	<h4>查看本学院教师</h4>
	<button id="getallteacher">查看</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="teachertable"></table> 
	</div>
	=================================================<br>
	<div>
	<h4>新增本学院课程</h4>
	课号：<input type="text" id="course_number" value="XXK00001"><br>
	课名：<input type="text" id="course_name" value="音乐鉴赏">
	<button id="addcourse">新增</button>
	</div>
	=================================================<br>
	<div>
	<h4>查看本学院所有课程</h4><button id="getallcourse">查看</button><br>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="allcoursetable"></table> <br><br>某学期开课：<input type="text" id="coursenum" value="">
	<select id="selectTerm" ></select><br>
	课程负责人工号（可空）：<input type="text" id="termcoursesn" value="10250007"><button id="addtermcourse">这学期开课</button>

	</div>
	=================================================<br>
	<div>
	<h4>查看本学院学期课程</h4>
	<select id="selectTerm2" ></select><button id="gettermcourse">查看</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="coursetable"></table>
	欲新增任课教师的开课id：<input type="text" id="kkid" value=""><br>
	新增任课教师：<input type="text" id="newcoursesn" value="10250007">开班限额：<input type="text" id="newcourseclass" value="-1"><button id="addnewcoursesn">给老师开课开课</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="termcourseteachertable"></table> 
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="termcourseclasstable"></table> 
	</div>
	=================================================<br>
	<div>
	<h4>Excel批量新增本学院课程</h4>
	建议您先<a href="../templet/dean_course.xlsx" target="_blank">下载模板</a>，编辑完模板之后在这里选择上传，系统会从第二行（含）之后开始添加数据。<br>
		<form method="POST" enctype="multipart/form-data" id="batchcourseform">
         	<input type="file" name="file" id="batchcoursefile"><br>
        </form>
        <button id="batchcoursebtn">批量增加课程</button>
	</div>
	=================================================<br>
	<div>
	<h4>Excel批量新增本学院教师</h4>
	建议您先<a href="../templet/dean_teacher.xlsx" target="_blank">下载模板</a>，编辑完模板之后在这里选择上传，系统会从第二行（含）之后开始添加数据。<br>
		<form method="POST" enctype="multipart/form-data" id="batchteacherform">
         	<input type="file" name="file" id="batchteacherfile"><br>
			<button id="batchteacherbtn">批量增加教师</button>
        </form>
	</div>
<script src="../js/acdemicdean.js"></script>
</body>
</html>