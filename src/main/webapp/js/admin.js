var tmpcollege=0;
$(function() {
	$("#selectTerm").hide();	
	$.getJSON("../getallterm",
			function(data) {
				for (var i = 0 ; i < data.length; i++) {
					$("#selectTerm").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
					}
				});
	

	$('#getallcollege')
		.click(
			function() {
				$("#collegetable").empty();
				$("#collegetable").append("<tr><td>编号</td><td>学员名</td><td>学院学期</td><td>操作</td></tr>");
				$.getJSON("getallcollege",
					function(data) {
						for (var i = 0, l = data.length; i < l; i++) {
							$("#collegetable").append("<tr><td>"+ data[i]["id"]+ "</td><td>"+ data[i]["name"] +"</td><td>"
								+ data[i]["currterm"]+ "</td><td><a href='#' onclick='showcoursedetail("+ data[i]["id"]+ ")'>课程详情</a>"
								+ "|<a href='#' onclick='showteacherdetail("+ data[i]["id"]+ ")'>教师详情</a>"
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
							+ "|<a href='setadmin?sn="+ data[i]["sn"]+ "'>设置其为系统管理员</a>"
							+ "|<a href='removeadmin?sn="+ data[i]["sn"]+ "'>取消其系统管理员身份</a>"
							+ "</td></tr>");
						}
					});
				});
	$("#selectTerm").change(function(){
		if(tmpcollege!=0){
			showcoursedetail(tmpcollege);
		}
	
	});
	$('#batchcollegebtn').click(function() {
        var formData = new FormData($("#batchcollegeform")[0]);

        if($("#batchcollegefile").val()===""){
            alert("请选择要上传的表格文件");
            return false;
        }else{
            $.ajax({
                url: 'batchaddcollege',
                type: 'POST',
                data:formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                	alert(returndata);
                },
                error: function (returndata) {
                    alert("上传失败!");
                }
            });
       }
	});
	
	$('#batchteacherbtn').click(function() {
        var formData = new FormData($("#batchteacherform")[0]);
        if($("#batchteacherfile").val()===""){
            alert("请选择要上传的文件");
            return false;
        }else{
            $.ajax({
                url: 'batchaddteacher',
                type: 'POST',
                data:formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                	alert(returndata);
                },
                error: function (returndata) {
                    alert("上传失败!");
                }
            });
       }
	});
});


function showteacherdetail(collegeid) {
	$("#selectTerm").hide();
	$("#collegeteachertable").empty();
	$("#collegeteachertable").append("<tr><td>工号</td><td>姓名</td><td>性别</td><td>操作</td></tr>");
	$.getJSON("getcollegeteacher?collegeid="+collegeid,
			function(data) {
				for (var i = 0, l = data.length; i < l; i++) {
					$("#collegeteachertable").append("<tr><td>"+ data[i]["sn"]+ "</td><td>"+ data[i]["name"] +"</td><td>"
						+ data[i]["sex"]+ "</td><td><a href='findateacher?sn="+ data[i]["sn"]+ "'>详情</a>"
						+ "|<a href='deleteteacher?sn="+ data[i]["sn"]+ "'>删除</a>"
						+ "|<a href='modifyteacher?sn="+ data[i]["sn"]+ "'>重置密码为学号</a>"
						+ "</td></tr>");
					}
				});
}
function showcoursedetail(collegeid) {
	$("#selectTerm").show();
	tmpcollege=collegeid;
	$("#collegeteachertable").empty();
	$("#collegeteachertable").append("<tr><td>课程编号</td><td>课程名</td><td>负责人工号</td><td>负责人姓名</td><td>操作</td></tr>");
	$.getJSON("getcollegecourse?term="+$("#selectTerm").val()+"&collegeid="+collegeid,
			function(data) {
				for (var i = 0, l = data.length; i < l; i++) {
					$("#collegeteachertable").append("<tr><td>"+ data[i]["number"]+ "</td><td>"+ data[i]["courseName"]+ "</td><td>"
							+ data[i]["sn"]+ "</td><td>"+ data[i]["teaName"]
							+ "</td><td><a href='getcourseteacher?term=2016-01&courseid="+ data[i]["number"]+ "'>详情</a>"
							+ "|<a href='deletecourse?number="+ data[i]["number"]+ "'>删除</a>"
							+ "|<a href='setcoursemaster?term=2016-01&courseid="+ data[i]["number"]
							+ "&sn=10250007'>设置课程负责人</a>"+ "</td></tr>");
							}
				});
	

}