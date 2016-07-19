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

<title>修改课程信息8</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<div class="container">
		<div class="row">
		<%@ include file="/include/course-modify-left.jsp"%>
			<div class="span10">
			<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="Teacher_Management_2_selectTchrCourse">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<form action="TeacherCourse_Modify_8_modifyNoteByCursId" method="post"
					enctype="multipart/form-data" class="form-horizontal">
					<div class="div-inf">
						<div class="div-inf-title">（一）与相关课程的分工衔接</div>
						<div class="div-inner-text">
							<textarea name="course.cursNote1"><s:property value="course.cursNote1"/></textarea>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">（二）其他说明</div>
						<div class="div-inner-text">
							<textarea name="course.cursNote2"><s:property value="course.cursNote2"/></textarea>
						</div>
					</div>
					<div class="div-btn">
							<input type="submit" value="提交" class="btn">
						</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
