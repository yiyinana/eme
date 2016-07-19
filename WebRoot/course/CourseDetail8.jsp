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

<title>课程详情8</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/course-detail.css" />

</head>

<body>
	<%@ include file="/include/course-detail-header.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/include/course-detail-left.jsp"%>
			<div class="span10">
				<div class="div-module-add-curs">
					<h6>
						<img class="image-path-1" src="img/circle.jpg" /> <a
							href="Course_1_1_goBack">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">说明</div>
					<div class="div-inner-text">
						<h5 class="gray">（一）与相关课程的分工衔接</h5>
						<label><s:property value="course.cursNote1" /></label>
						<h5 class="gray">（二）其他说明</h5>
						<label><s:property value="course.cursNote2" /></label>
						<label></label>
					</div>
				</div>
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
