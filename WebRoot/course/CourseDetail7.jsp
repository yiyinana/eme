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

<title>课程详情7</title>

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
					<div class="div-inf-title">教材及参考书目</div>
					<div class="div-inner-text">
						<h5 class="gray">教材：</h5>
						<ol type="1">
							<s:iterator value="cm" var="m">
							<li><s:property value="#m.cmAuthor" />，
							《<s:property value="#m.cmName" />》，
							<s:property value="#m.cmPublisher" />，
							<s:property value="#m.cmPubYear" /></li>
							</s:iterator>
						</ol>
						<h5 class="gray">参考书目：</h5>
						<ol type="1">
							<s:iterator value="crb" var="c">
							<li><s:property value="#c.crbAuthor" />，
							《<s:property value="#c.crbName" />》，
							<s:property value="#c.crbPublisher" />，
							<s:property value="#c.crbPubYear" /></li>
							</s:iterator>
						</ol>
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
