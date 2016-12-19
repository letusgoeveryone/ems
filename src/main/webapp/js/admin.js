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
});