<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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

<title>课程详情1</title>

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
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="Course_1_1_goBack">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">基本信息</div>
					<div class="div-detail">
						<label>课程编号：</label> <label><s:property value="course.cursNum"/></label>
					</div>
					<div class="div-detail">
						<label>课程名称：</label> <label><s:property value="course.cursName"/></label>
					</div>
					<div class="div-detail">
						<label>英文名称：</label> <label><s:property value="course.cursEngName"/></label>
					</div>
					<div class="div-detail">
						<label>学分/学时：</label> <label><s:property value="course.cursCredit"/>/<s:property value="course.cursClassHour"/></label>
					</div>
					<div class="div-detail">
						<label>课程性质：</label> <label><s:property value="course.cursProperty"/></label>
					</div>
					<div class="div-detail">
						<label>适用专业：</label> <label><s:property value="course.cursApplMajor"/></label>
					</div>
					<div class="div-detail">
						<label>开设学期：</label> <label><s:property value="course.cursTerm"/></label>
					</div>
					<div class="div-detail">
						<label>先修课程：</label> <label><s:property value="course.cursPreCourses"/></label>
					</div>
					<div class="div-detail">
						<label>开课单位：</label> <label><s:property value="course.dept.deptName"/></label>
					</div>
				</div>

				<div class="div-inf">
					<div class="div-inf-title">课程简介</div>
					<div class="div-inner-text">
						<s:property value="course.cursIntro"></s:property>
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
