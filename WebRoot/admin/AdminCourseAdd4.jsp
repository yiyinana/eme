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

<title>添加课程信息4</title>

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
	var i = 1;
	function addContent() {
		i++;
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label>"
				+ i + "</label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' name='ctm["
			+ (i - 1) + "].cursContent' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' name='ctm["
			+ (i - 1) + "].period' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='checkbox' value='讲授' name='ctm["
			+ (i - 1)
			+ "].teacMethod'>讲授&nbsp;&nbsp;<input type='checkbox' value='讨论' name='ctm["
			+ (i - 1)
			+ "].teacMethod'>讨论&nbsp;&nbsp;<input type='checkbox' value='实验' name='ctm["
			+ (i - 1) + "].teacMethod'>实验</div>";
		var hr = document.createElement("hr");
		document.getElementById("div-method").appendChild(div1);
		document.getElementById("div-method").appendChild(div2);
		document.getElementById("div-method").appendChild(div3);
		document.getElementById("div-method").appendChild(div4);
		document.getElementById("div-method").appendChild(hr);
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
							href="AdminCourse_List_1_selectAllCurs">课程信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>添加课程教学方式
					</h6>
				</div>
				<form action="AdminCourse_Add_5_addTchingModeByCursId" method="post"
					class="form-horizontal">
					
					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-method">
							<div class="control-group">
								<label class="control-label">序号：</label>
								<div class="controls">
									<label>1</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">课程内容：</label>
								<div class="controls">
									<input type="text" name="ctm[0].cursContent" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学时：</label>
								<div class="controls">
									<input type="text" name="ctm[0].period" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">教学方式：</label>
								<div class="controls">
									<input type="checkbox" value="讲授" name="ctm[0].teacMethod">讲授&nbsp;&nbsp;
									<input type="checkbox" value="讨论" name="ctm[0].teacMethod">讨论&nbsp;&nbsp;
									<input type="checkbox" value="实验" name="ctm[0].teacMethod">实验&nbsp;&nbsp;
								</div>
							</div>
							<hr>
						</div>
						<div class="div-btn">
							<label class="btn" onclick="addContent()">添加</label> <input
								type="submit" value="下一步" class="btn">
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
