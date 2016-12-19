<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教务管理员</title>
<script src="../js/jquery.js"></script>

</head>
<body>
	
	<h2>教务管理员</h2>
	<a href="../">首页</a>|<a href="../logout">登出</a><br>
	=================================================<br>
	<div>
	<h4>新增教师</h4>
	工号：<input type="text" id="username" value="10250007"><br>
	姓名：<input type="text" id="teachername" value="王红涛"><br>
	密码：<input type="text" id="password" value="123456">
	<button id="addteacher">新增</button>
	</div>
	=================================================<br>
	<div>
	<h4>查看教师</h4>
	<button id="getallteacher">查看</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="teachertable"></table> 
	</div>
	=================================================<br>
	<div>
	<h4>新增课程</h4>
	课号：<input type="text" id="course_number" value="XXK00001"><br>
	课名：<input type="text" id="course_name" value="音乐鉴赏">
	<button id="addcourse">新增</button>
	</div>
	=================================================<br>
	<div>
	<h4>查看课程</h4>
	<button id="getallcourse">查看</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="coursetable"></table> 
	</div>
	
<script src="../js/acdemicdean.js"></script>
</body>
</html>