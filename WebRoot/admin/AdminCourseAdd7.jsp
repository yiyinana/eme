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

<title>添加课程信息7</title>

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
		div1.innerHTML += "<label class='control-label'>作者：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbAuthor' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>书名：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbName' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>出版社：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbPublisher' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>出版年份：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbPubYear' class='input-long'></div>";
		var hr = document.createElement("hr");
		document.getElementById("div-content").appendChild(div1);
		document.getElementById("div-content").appendChild(div2);
		document.getElementById("div-content").appendChild(div3);
		document.getElementById("div-content").appendChild(div4);
		document.getElementById("div-content").appendChild(hr);
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
							href="AdminCourse_List_1_selectAllCurs">课程信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>添加课程教材及参考书目
					</h6>
				</div>
				<form action="AdminCourse_Add_8_addRefBookByCursId" method="post" class="form-horizontal">
					<div class="div-inf">
						<div id="div-content">
							<div class="control-group">
								<label class="control-label">教材：</label>
								<div class="controls">
									<input type="text" name="course.cursMaterial" class="input-long">
								</div>
							</div>
							
							<hr>
						<!-- 课程参考书目 -->
						<div class="div-inf-title">参考书目</div>
							<div class="control-group">
								<label class="control-label">作者：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbAuthor" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">书名：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbName" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">出版社：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbPublisher" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">出版年份：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbPubYear" class="input-long">
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
