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

<title>教师信息列表</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript" src="js/tchrListPage.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h6><img class="image-path-1" src="img/circle.jpg"/><a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/>教师信息管理</h6>
				</div>
				<form class="form-search"
					action="AdminTeacher_List_1_searchTeachers" method="post">
					<div class="div-select">
						<label>工号</label> <input type="text" name="limits.tchrSchNum">
					</div>
					<div class="div-select">
						<label>姓名</label> <input type="text" name="limits.tchrName">
					</div>
					<div class="div-select-s">
						<label>职称</label> <select name="limits.tchrTitle">
							<option value="教授" selected="selected">教授</option>
							<option value="副教授">副教授</option>
							<option value="高级工程师">高级工程师</option>
							<option value="讲师">讲师</option>
						</select>
					</div>
					<button class="btn">查找</button>
				</form>
				<div class="div-msg">
					<label><s:actionerror /></label> <a class="btn top0"
						href="admin/AdminTeacherAdd1.jsp">新增教师信息</a>
				</div>
				<div class="div-tchr-detail" id="adminTchrList">
					<s:iterator value="teachers" var="t">
						<section class="div-tchr">
							<img class="img-polaroid img-head"
								src="tchrImg/<s:property value="#t.tchrSchNum"/>.jpg">
							<div class="div-tchr-detail-inf">
								<h6>姓名：</h6>
								<label><s:property value="#t.tchrName" /></label>
							</div>
							<div class="div-tchr-detail-inf">
								<h6>工号：</h6>
								<label><s:property value="#t.tchrSchNum" /></label>
							</div>
							<div class="div-tchr-detail-inf">
								<h6>职称：</h6>
								<label><s:property value="#t.tchrTitle" /></label>
							</div>
							<div class="div-tchr-detail-inf">
								<h6>电话：</h6>
								<label><s:property value="#t.tchrPhone" /></label>
							</div>
							<div class="div-tchr-detail-inf">
								<h6>办公室：</h6>
								<label><s:property value="#t.tchrOfficeAddr" /></label>
							</div>
							<div class="div-tchr-detail-inf a">
								<a
									href="AdminTeacher_Information_1_selectTeacherBySchNum?tchrSchNum=<s:property value="#t.tchrSchNum"/>">详细</a>&nbsp;&nbsp;
								<a
									href="AdminTeacher_Information_Modify_selectTeacherBySchNum?tchrSchNum=<s:property value="#t.tchrSchNum"/>">修改</a>&nbsp;&nbsp;
								<a onclick="return confirm('确认删除？')"
									href="AdminTeacher_Information_1_deleteBySchNum?tchrSchNum=<s:property value="#t.tchrSchNum"/>">删除</a>
							</div>
							<hr />
						</section>
					</s:iterator>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	
	<script src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
