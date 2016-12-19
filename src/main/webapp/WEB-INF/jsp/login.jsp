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
	<input type="text" id="username" value="system">
	<br> 密 码：
	<input type="text" id="password" value="123456">
	<br> 验证码：
	<input type="text" id="captcha" value="">
	<br>
	<img src="createImage" alt="看不清？点击换一张" id="captchaimg" />
	<br>
	<span id="loginMsg"></span>

	<button id="loginbtn">登录</button>
	<script type="text/javascript">
		$(function() {
			$('#captchaimg').click(
					function() {
						$("#captchaimg").attr("src",
								"createImage?dt=" + Math.random()); //随机生成验证码
						return false;
					});
			$('#loginbtn').click(function() {
                $.ajax({
                    url: "logincheck",
                    type: "POST",
                    data:{username:$("#username").val(),password:hex_md5($("#password").val()),captcha:$("#captcha").val()},
                    success: function (data) {
                    	if (data === "Loginok") {
    						$("#loginMsg").html("登录成功!");
    						window.location.href = "loginsuccess";
    					};
    					if (data === "CheckCodeError") {
    						$("#loginMsg").html("验证码出错了!");
    						$("#captchaimg").val("");
    					};
    					if (data === "LoginError") {
    						$("#loginMsg").html("输入的账户密码错误，请重试...");
    						$("#password").val("");
    						updateCcdImage();
    					};
    					if (data === "RoleError") {
    						$("#loginMsg").html("权限出错，请联系管理员...");
    						$("#password").val("");
    						updateCcdImage();
    					};
                    },
                    error: function () {
                        alert("失败!");
                    }
                });

			});
		});
	</script>
</body>
</html>