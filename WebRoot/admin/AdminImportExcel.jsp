<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>信息批量导入</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function check(a) {
		var form = a.parentNode;
		var filename = form.getElementsByClassName("filename")[0].value;
		if (filename == "") {
			alert("请选择文件！");
			return false;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "xls" && postf != "xlsx") {
			alert("您选择的文件格式不正确！");
			return false;
		}
		form.getElementsByClassName("fileName")[0].value = filename;
		return true;
	}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h4>
						<a href="admin/AdminHome1.jsp">首页</a>>信息批量导入
					</h4>
					<hr />
				</div>
				<div class="main">
						<h6>学生信息导入</h6>
						<form action="Admin_Import_Excel_addStuExcel" method="post" enctype="multipart/form-data">
						<a class="btn btn-file">选择文件<input type="file" name="file" class="filename"></a>
						<input name="uploadUrl" value="/stuTchrCurs" class="hidden" /> <input
							class="fileName hidden" name="fileName" /> 
						<input type="submit" class="btn" value="开始上传" onclick="return check(this)">
						</form>
						<hr>
						<h6>学生-课程信息导入</h6>
						<form action="Admin_Import_Excel_addStuCursExcel" method="post" enctype="multipart/form-data">
						<a class="btn btn-file">选择文件<input type="file" name="file" class="filename"></a>
						<input name="uploadUrl" value="/stuTchrCurs" class="hidden" /> <input
							class="fileName hidden" name="fileName" /> 
						<input type="submit" class="btn" value="开始上传" onclick="return check(this)">
						</form>
						<hr>
						<h6>教师信息导入</h6>
						<form action="Admin_Import_Excel_addTchrExcel" method="post" enctype="multipart/form-data">
						<a class="btn btn-file">选择文件<input type="file" name="file" class="filename"></a>
						<input name="uploadUrl" value="/stuTchrCurs" class="hidden" /> <input
							class="fileName hidden" name="fileName" /> 
						<input type="submit" class="btn" value="开始上传" onclick="return check(this)">
						</form>
						<hr>
						<h6>教师-课程信息导入</h6>
						<form action="Admin_Import_Excel_addTchrCursExcel" method="post" enctype="multipart/form-data">
						<a class="btn btn-file">选择文件<input type="file" name="file" class="filename"></a>
						<input name="uploadUrl" value="/stuTchrCurs" class="hidden" /> <input
							class="fileName hidden" name="fileName" /> 
						<input type="submit" class="btn" value="开始上传" onclick="return check(this)">
						</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
