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

<title>添加课程信息2</title>

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
var i=1;
function addTarget() {
	i++;
	var div = document.createElement("div");
	div.setAttribute("class", "control-group");
	div.innerHTML += "<label class='control-label'>教学目标"
		+ i + "：</label><div class='controls'><input type='text' name='targets["+(i-1)+"].tchTarContent'  class='input-long'></div>";
	document.getElementById("div-targets").appendChild(div);
}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10">
			<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminCourse_List_1_selectAllCurs">课程信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>添加课程教学目标
					</h6>
				</div>
				<form action="AdminCourse_Add_9_addTchingTargetByCursId" method="post" class="form-horizontal">
					<div class="div-inf">
					<p>通过本课程的理论教学，使学生具备以下能力：</p>
					<div id="div-targets">
					<div class="control-group">
							<label class="control-label">教学目标1：</label>
							<div class="controls">
								<input type="text" name="targets[0].tchTarContent" class="input-long">
							</div>
							
						</div>
					</div>
					<div class="div-btn">
				<label class="btn" onclick="addTarget()">添加</label>
				<input type="submit" value="下一步" class="btn">
			</div>
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
