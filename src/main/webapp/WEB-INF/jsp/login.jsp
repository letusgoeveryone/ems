<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script src="js/jquery.js"></script>
<script src="js/md5.js"></script>
</head>
<body>
	<h2>登录</h2>
	用户名：
	<input type="text" id="username" value="10250007">
	<br> 密 码：
	<input type="text" id="password" value="123456">
	<br> 验证码：
	<input type="text" id="captcha" value="">
	<br>
	<img src="createImage" alt="看不清？点击换一张" id="captchaimg" />
	<br>
	<span id="loginMsg"></span>
	<button id="loginbtn">登录</button>
	<script src="js/login.js"></script>
</body>
</html>