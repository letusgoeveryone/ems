<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理员</title>
<script src="../js/jquery.js"></script>
</head>
<body>
	<h2>系统管理员</h2>
	<a href="../">首页</a>|<a href="../logout">登出</a><br>
	=================================================<br>
	<div>
	<h4>新增学院</h4>
	学院：<input type="text" id="collegename" value="软件学院"><br>
	学期：<input type="text" id="collegeterm" value="2016-01">
	<button id="addcollege">新增</button>
	</div>
	=================================================<br>
	<div>
	<h4>查看学院</h4>
	<button id="getallcollege">查看</button>
	<table border="1px" cellPadding="0px" cellSpacing="0px" id="collegetable"></table> 
	</div>
	<script src="../js/admin.js"></script>
</body>
</html>