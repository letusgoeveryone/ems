function initLogin() {
        var doc_height = $(document).height(); //获取文档窗口的高度
        var footer_height = $("footer").height(); //获取底部footer的高度
        var body_height = doc_height - footer_height - 10; //计算body的高度
        //-10是为了让底部空出10个像素的边，配合body的padding-bottom的10px。
        //获取登录窗口的高度
        var login_container_height = $("#login-container").height();
        //设置body的高度
        $(body).height(body_height);
        //设置登录窗口上下居中
        $("#login-container").css("margin-top", (body_height - login_container_height) / 2);

    }
    
    function postData() {
    	//测试弹出modal信息，正式程序可以把下面这行删除
    	//$('#myModal').modal('toggle');
    	//如果下面的代码没有问题，可以直接解除注释
    	//下面这段代码是为了向服务器提交id和密码进行验证
        $.post(
            "logincheck", {
                username: $("#id").val(), password: hex_md5($("#pw").val()) //,ccd: $("#ccd").val()
            },
            function(data) {
                if (data === "LoginError") {
                	$('#myModal').modal('toggle');//登录信息有误，则弹出登录错误的提示信息
                };
                if (data === "Loginok") {//登录信息正确，则转入下一个网页
                	window.location.href = "loginsuccess";
                };
            }
        );
    }

    function login() {
    	// 下面两个变量表示两个输入框的值是否为空，默认为true
        var idIsNull = true;
        var pwIsNull = true;

        if ($('#id').val().length === 0) {
            showHint($('#id_reminder'));//为空，显示提示信息
        } else {
            idIsNull = false;//说明为不空
        }

        if ($('#pw').val().length === 0) {//为空，显示提示信息
            showHint($('#pw_reminder'));
        } else {
            pwIsNull = false;//说明为不空
        }

        if (!idIsNull && !pwIsNull) {//都不空的时候发送数据到服务器验证
            postData();
        }
    }

    function hideHint(obj) {//隐藏提示信息
        obj.css("visibility", "hidden");
    }

    function showHint(obj) {//显示提示信息
        obj.css("visibility", "visible");
    }

        $(function() {
        initLogin();
        setBackground($("body"));
        // 设置窗口的大小改变后，“登录”窗口的居中
        $(window).resize(initLogin);

    });