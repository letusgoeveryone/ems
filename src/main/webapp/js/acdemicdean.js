$(function() {
	$('#addteacher').click(function() {
		$.post("addteacher", {
			sn : $("#username").val(),
			name : $("#teachername").val(),
			password : $("#password").val()
		}, function(data) {
			if (data == "true") {
				alert("新增教师成功");
			} else {
				alert("新增教师失败");
			}
			;
		});
	});
	$('#addcourse').click(function() {
		$.post("addcourse", {
			number : $("#course_number").val(),
			name : $("#course_name").val()
		}, function(data) {
			if (data == "true") {
				alert("新增课程成功");
			} else {
				alert("新增课程失败");
			}
			;
		});
	});
	$('#getallteacher')
		.click(
			function() {
				$("#teachertable").empty();
				$("#teachertable").append("<tr><td>工号</td><td>姓名</td><td>性别</td><td>操作</td></tr>");
				$.getJSON("getallteacher",
					function(data) {
						for (var i = 0, l = data.length; i < l; i++) {
							$("#teachertable").append("<tr><td>"+ data[i]["sn"]+ "</td><td>"+ data[i]["name"] +"</td><td>"
								+ data[i]["sex"]+ "</td><td><a href='findateacher?sn="+ data[i]["sn"]+ "'>详情</a>"
								+ "|<a href='deleteteacher?sn="+ data[i]["sn"]+ "'>删除</a>"
								+ "|<a href='modifyteacher?sn="+ data[i]["sn"]+ "'>重置密码为学号</a>"
								+ "</td></tr>");
							}
						});
					});
	$('#getallcourse')
		.click(
			function() {
				$("#coursetable").empty();
				$("#coursetable").append("<tr><td>课程编号</td><td>课程名</td><td>负责人工号</td><td>负责人姓名</td><td>操作</td></tr>")
				$.getJSON("termcoursemaster?term=2016-01",
					function(data) {
						for (var i = 0, l = data.length; i < l; i++) {
							$("#coursetable").append("<tr><td>"+ data[i]["number"]+ "</td><td>"+ data[i]["courseName"]+ "</td><td>"
							+ data[i]["sn"]+ "</td><td>"+ data[i]["teaName"]
							+ "</td><td><a href='getcourseteacher?term=2016-01&courseid="+ data[i]["number"]+ "'>详情</a>"
							+ "|<a href='deletecourse?number="+ data[i]["number"]+ "'>删除</a>"
							+ "|<a href='setcoursemaster?term=2016-01&courseid="+ data[i]["number"]
							+ "&sn=10250007'>设置课程负责人</a>"+ "</td></tr>");
							}
					});
		});
});