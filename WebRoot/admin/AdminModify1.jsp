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
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	function isEqual(){
		var pwd1 = document.getElementById("password1").value;
		var pwd2 = document.getElementById("password2").value;
		if(pwd1 == pwd2){
			return true;
		}
		else {
			alert("两次输入密码不同！");
			return false;
		}
	}
	function show(){
		document.getElementById("form-add-admin").style.display="block";
	}
	
	function isAddEqual(){
		var pwd1 = document.getElementById("addPassword1").value;
		var pwd2 = document.getElementById("addPassword2").value;
		if(pwd1 == pwd2){
			return true;
		}
		else {
			alert("两次输入密码不同！");
			return false;
		}
	}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h4>
						<a href="admin/AdminHome1.jsp">首页</a>>信息设置
					</h4>
					<hr />
				</div>
				<div class="main">

					<h6>修改密码</h6>
					<form action="Admin_Modify_1_updatePwd" method="post" class="form-horizontal form-add">
						<div class="control-group">
							<label class="control-label">请输入密码：</label>
							<div class="controls">
								<input type="password" name="adminPwd" id="password1">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">请再次输入密码：</label>
							<div class="controls">
								<input type="password" id="password2">
							</div>
						</div>
						<div class="margin12">
							<input onclick="return isEqual()" type="submit" class="btn" />
						</div>
					</form>
					<hr>
					<h6>用户管理</h6>
					<div class="div-admin-list">
					<s:iterator value="admins" var="a">
					<div class="div-tchr">
						<img class="img-admin" src="img/admin.png">
						<div class="div-admin-edit">
							<h6>姓名：</h6>
							<label><s:property value="#a.adminName"/></label>
						</div>
						<div class="div-admin-edit">
							<h6>工号：</h6>
							<label><s:property value="#a.adminSchNum"/></label>
						</div>
						<div class="div-admin-edit">
							<a onclick="return confirm('确认删除？')"
								href="Admin_Modify_1_deleteBySchNum?adminSchNum=<s:property value="#a.adminSchNum"/>">删除用户</a>
						</div>
						<hr />
					</div>
					</s:iterator>					
					</div>
					<div class="div-add-admin"><a onclick="show()">创建一个管理员账户</a></div>
					<form action="Admin_Modify_1_addAdmin" method="post" class="form-horizontal form-add" id="form-add-admin">
						<div class="control-group">
							<label class="control-label">工号：</label>
							<div class="controls">
								<input type="text" name="admin.adminSchNum">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input type="text" name="admin.adminName">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">请输入密码：</label>
							<div class="controls">
								<input type="password" name="admin.adminPwd" id="addPassword1">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">请再次输入密码：</label>
							<div class="controls">
								<input type="password" id="addPassword2">
							</div>
						</div>
						<div class="margin12">
							<input onclick="return isAddEqual()" type="submit" class="btn" />
						</div>
					</form>
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
