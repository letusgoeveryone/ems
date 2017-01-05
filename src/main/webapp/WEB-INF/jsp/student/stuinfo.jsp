<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>学生个人信息维护</h2>
	<a href="../">首页</a>
	|
	<a href="../logout">登出</a>
	<br> =================================================
	<form action="modifyinfo" method="POST">
		<br> 姓名
		<input type="text" name="name" value="${name}">
		<br> 性别
		<input type="text" name="sex" value="${sex}">
		<br> QQ
		<input type="text" name="qq" value="${qq}">
		<br> 电话
		<input type="text" name="tel" value="${tel}">
		<br> E-Mail
		<input type="text" name="email" value="${email}">
		<br>
		<input type="submit" value="确认修改">
		<a href="">返回</a>
	</form>


</body>
</html>

