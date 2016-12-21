$(function() {
	$("#selectTerm").hide();
	$("#selectTerm2").hide();
	$.getJSON("../getallterm",
			function(data) {
				for (var i = 0 ; i < data.length; i++) {
					$("#selectTerm").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
					$("#selectTerm2").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
					}
				});
	$.post("../getcurrentterm",
			function(data) {
		 			$("#selectTerm option[value="+data+"]").attr("selected", "true"); 
		 			$("#selectTerm2 option[value="+data+"]").attr("selected", "true"); 
				});	

	$('#addteacher').click(function() {
		$.post("addteacher", {
			sn : $("#username").val(),
			name : $("#teachername").val(),
			password : hex_md5($("#password").val())
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
	$('#gettermcourse')
		.click(
			function() {
				$("#selectTerm2").show();
				$.post("../getcurrentterm",
					function(data) {
							$("#selectTerm2 option[value="+data+"]").attr("selected", "false"); 
				 			$("#selectTerm2 option[value="+data+"]").attr("selected", "true"); 
						});	
				showtermcourse();
		});
	
	$('#getallcourse')
	.click(
		function() {
			$("#selectTerm").show();
			$("#allcoursetable").empty();
			$("#allcoursetable").append("<tr><td>课程id</td><td>课程编号</td><td>课程名</td><td>操作</td></tr>");
			$.getJSON("getcollegecourse",
					function(data) {
						for (var i = 0, l = data.length; i < l; i++) {
							$("#allcoursetable").append("<tr><td>"+ data[i]["sn"]+ "</td><td>"+ data[i]["number"] +"</td><td>"+ data[i]["courseName"] +"</td><td>"
								+ "<a href='#' onclick=\"settermcourse('"+ data[i]["id"]+ "')\">设置开课</a>"
								+ "</td></tr>");
							}
						});
			
	});
	
	$("#selectTerm2").change(function(){
		showtermcourse();
	});
	
	$('#addtermcourse').click(function() {
		$.post("settermcourse", {
			teachersn : $("#termcoursesn").val(),
			courseId : $("#coursenum").val(),
			term : $("#selectTerm").val()
		}, function(data) {
			if (data == "true") {
				alert("新增学期课程成功");
			} else {
				alert("新增学期课程失败");
			}
			;
		});
	});
});

function showtermcourse() {
	$("#coursetable").empty();
	$("#coursetable").append("<tr><td>课程编号</td><td>课程名</td><td>负责人工号</td><td>负责人姓名</td><td>操作</td></tr>")
	$.getJSON("termcoursemaster?term="+$("#selectTerm2").val(),
		function(data) {
			for (var i = 0, l = data.length; i < l; i++) {
				$("#coursetable").append("<tr><td>"+ data[i]["id"]+ "</td><td>"+ data[i]["courseName"]+ "</td><td>"
				+ data[i]["sn"]+ "</td><td>"+ data[i]["teaName"]
				+ "</td><td><a href='getcourseteacher?term="+$("#selectTerm2").val()+"&courseid="+ data[i]["number"]+ "'>详情</a>"
				+ "|<a href='deletetermcourse?id="+ data[i]["id"]+ "'>删除</a>"
				+ "|<a href='setcoursemaster?term="+$("#selectTerm2").val()+"&courseid="+ data[i]["number"]
				+ "&sn=10250007'>设置课程负责人</a>"+ "</td></tr>");
				}
		});
}

function settermcourse(collegeid) {
	$("#coursenum").val(collegeid);	
}