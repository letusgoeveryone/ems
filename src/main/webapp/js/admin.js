$(function() {
	$('#getallcollege')
		.click(
			function() {
				$("#collegetable").empty();
				$("#collegetable").append("<tr><td>编号</td><td>学员名</td><td>学院学期</td><td>操作</td></tr>");
				$.getJSON("getallcollege",
					function(data) {
						for (var i = 0, l = data.length; i < l; i++) {
							$("#collegetable").append("<tr><td>"+ data[i]["id"]+ "</td><td>"+ data[i]["name"] +"</td><td>"
								+ data[i]["currterm"]+ "</td><td><a href='findateacher?sn="+ data[i]["id"]+ "'>详情</a>"
								+ "|<a href='modifyteacher?sn="+ data[i]["id"]+ "'>删除</a>"
								+ "</td></tr>");
							}
						});
					});
	$('#addteacher').click(function() {
		$.post("addteacher", {
			sn : $("#sn").val(),
			name : $("#name").val(),
			password : hex_md5($("#password").val()),
			collegeid:$("#collegeid").val()
		}, function(data) {
			if (data == "true") {
				alert("新增教师成功");
			} else {
				alert("新增教师失败");
			}
			;
		});
	});
	$('#getallteacher')
	.click(
		function() {
			$("#teachertable").empty();
			$("#teachertable").append("<tr><td>工号</td><td>姓名</td><td>性别</td><td>学院</td><td>操作</td></tr>");
			$.getJSON("getallteacher",
				function(data) {
					for (var i = 0, l = data.length; i < l; i++) {
						$("#teachertable").append("<tr><td>"+ data[i]["sn"]+ "</td><td>"+ data[i]["name"]+"</td><td>"
							+ data[i]["sex"] +"</td><td>"+ data[i]["college"]+ "</td><td><a href='findateacher?sn="+ data[i]["sn"]+ "'>详情</a>"
							+ "|<a href='deleteteacher?sn="+ data[i]["sn"]+ "'>删除</a>"
							+ "|<a href='modifyteacher?sn="+ data[i]["sn"]+ "'>重置密码为学号</a>"
							+ "|<a href='setacdemicdean?sn="+ data[i]["sn"]+ "'>设置其为教务管理员</a>"
							+ "|<a href='removeacdemicdean?sn="+ data[i]["sn"]+ "'>取消其教务管理员身份</a>"
							+ "</td></tr>");
						}
					});
				});
});