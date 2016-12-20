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


				$("#captchaimg").attr("src","createImage?dt=" + Math.random()); //刷新验证码

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