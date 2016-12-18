<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
    </head>
<body>
<h2>Hello World!</h2>
<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
<a href="login" >登录</a><br>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_STUDENT')"> <a href="student/" >学生页面</a><br> </sec:authorize>
<sec:authorize access="hasRole('ROLE_TEACHER')"><a href="teacher/" >教师页面</a><br></sec:authorize>
<sec:authorize access="hasRole('ROLE_ACDEMICDEAN')"> <a href="acdemicdean/">教务管理员页面</a><br> </sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="admin/">系统管理员页面</a><br> </sec:authorize>
<sec:authorize access="hasRole('ROLE_ACDEMICDEAN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')">
<a href="logout">登出</a><br>
</sec:authorize>
<br>
<a href="test_insert_a_college">测试插入一条学院和教师数据</a>
</body>
</html>