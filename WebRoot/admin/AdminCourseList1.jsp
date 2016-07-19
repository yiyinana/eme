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

<title>课程列表</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript" src="js/cursListPage.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h6>
						<img class="image-path-1" src="img/circle.jpg" /><a
							href="admin/AdminHome1.jsp">首页</a><img class="image-path-2"
							src="img/zhexian.jpg" />课程信息管理
					</h6>
				</div>
				<form class="form-search" action="AdminCourse_List_1_searchCourses"
					method="post">
					<div class="div-select">
						<label>课程编号</label> <input type="text" name="limits.cursNum">
					</div>
					<div class="div-select">
						<label>课程名称</label> <input type="text" name="limits.cursName">
					</div>
					<button class="btn">查找</button>
				</form>
				<div class="div-msg">
					<label><s:actionerror /></label> <a class="btn top0"
						href="AdminCourse_Add_1_selectDeptmentAndThr">新增课程信息</a>
				</div>
				<div class="div-tchr-detail" id="adminCursList">
					<s:iterator value="courses" var="c">
						<div class="div-curs">
							<h6>课程名称：</h6>
							<label><s:property value="#c.cursName" /></label><br />
							<h6>课程编号：</h6>
							<label><s:property value="#c.cursNum" /></label><br />
							<h6>学分/学时：</h6>
							<label><s:property value="#c.cursCredit" />/<s:property
									value="#c.cursClassHour" /></label><br />
							<h6>课程性质：</h6>
							<label><s:property value="#c.cursProperty" /></label><br />
							<h6>开设学期：</h6>
							<label><s:property value="#c.cursTerm" /></label>
							<div class="div-curs-detail-inf a">
								<a
									href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>">详细</a>&nbsp;&nbsp;
								<a
									href="AdminCourse_Modify_1_selectByCursId?cursId=<s:property value="#c.cursId"/>">修改</a>&nbsp;&nbsp;
								<a
									href="AdminCourse_List_1_deleteByCursId?cursId=<s:property value="#c.cursId"/>"
									onclick="return confirm('确认删除？')">删除</a>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
