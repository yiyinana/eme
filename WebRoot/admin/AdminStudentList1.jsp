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

<title>学生信息列表</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script src="js/jquery1.12.1.js"></script>
<script src="js/stuListPage.js"></script>
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
					<h6><img class="image-path-1" src="img/circle.jpg"/><a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/>学生信息管理</h6>
				</div>
				<form action="AdminStudent_List_1_searchStudents"
					class="form-search" method="post">
					<div class="div-select">
						<label>学号</label> <input type="text" name="limits.stuSchNum">
					</div>
					<div class="div-select">
						<label>姓名</label> <input type="text" name="limits.stuName">
					</div>
					<div class="div-select-s">
						<label>班级</label> <select name="limits.stuClazz">
							<option value="" selected="selected">全部</option>
							<s:iterator value="allClazz" var="c">
							<option value="<s:property value="#c.claId"/>"><s:property value="#c.claName"/></option>
							</s:iterator>
						</select>
					</div>
					<button class="btn">查找</button>
				</form>
				<div class="div-msg">
					<label><s:actionerror /></label> <a class="btn top0"
						href="AdminStudent_Add_1_selectAllClazz">新增学生信息</a>
				</div>
				<div class="div-tchr-detail">
					<table class="table table-bordered table-condensed" id="adminStuList">
						<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>班级</th>
								<th>电话</th>
								<th>宿舍</th>
								<th class="th-opr">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="students" var="s">
								<tr>
									<td><s:property value="#s.stuSchNum" /></td>
									<td><s:property value="#s.stuName" /></td>
									<td><s:if test="%{#s.stuGender==true}">男</s:if> 
										<s:else>女</s:else>
										</td>
									<td><s:property value="#s.clazz.claName" /></td>
									<td><s:property value="#s.stuPhone" /></td>
									<td><s:property value="#s.stuCommAddr" /></td>
									<td class="th-opr"><a
										href="AdminStudent_Information_1_selectStudentBySchNum?schNum=<s:property value="#s.stuSchNum"/>">详细</a>&nbsp;&nbsp;
										<a
										href="AdminStudent_Information_Modify_selectStudentBySchNum?schNum=<s:property value="#s.stuSchNum"/>">编辑</a>&nbsp;&nbsp;
										<a onclick="return confirm('确认删除？')"
										href="AdminStudent_List_1_deleteBySchNum?schNum=<s:property value="#s.stuSchNum"/>">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
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
