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

<title>课程详情3</title>

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
					<div class="div-inf-title">课程具体内容及基本要求</div>
				</div>
				<s:iterator value="csc" var="c" status="s">
					<div class="div-curs-detail">
						<h5 class="gray">（<s:property value="#s.index+1" />）<s:property value="#c.cscChapter" />（<s:property value="#c.cscClassHour" />学时）（支撑<s:property value="#c.cscTarget" />）</h5>
						<label><s:property value="#c.cscGoal" /></label><br /> <label>1.基本要求</label><br />
						<label><s:property value="#c.cscBasRequ" /></label><br /> <label>2.重点、难点</label>
						<br /> <label>重点：<s:property value="#c.cscStudyEmpha" /></label><br /> <label>难点：<s:property value="#c.cscStudyDiffi" /></label><br /> <label>3.作业及课外学习要求：</label><br />
						<label><s:property value="#c.cscHomework" /></label><br />
					</div>
				</s:iterator>
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
