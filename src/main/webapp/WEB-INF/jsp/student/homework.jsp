<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>学生作业提交</h2>
	<br>===========================================================================
	<form action="uploadHW" method="post" enctype="multipart/form-data">
		<%-- 类型enctype用multipart/form-data，这样可以把文件中的数据作为流式数据上传，不管是什么文件类型，均可上传。--%>
		请选择要上传的文件
		<input type="file" name="file">
		<br>--------------------------------------------------<br>
		<input type="submit" value="上传作业">
		<br>
	</form>

</body>
</html>