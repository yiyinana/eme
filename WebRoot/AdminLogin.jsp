<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录达成度评估系统</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link href="css/adminlogin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
 	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	} 
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	function isNull() {
		var userName = document.getElementById("inputName").value;
		var password = document.getElementById("inputPassword").value;
		if (userName == "" || password == "") {
			alert("用户名或密码不能为空！");
			return false;
		} else
			return true;
	}
	</script>
</head>

<body
	style="background-color:#1c77ac; background-image:url(img/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
	<div class="logintop">
		<span>欢迎登录达成度评估系统</span>
	</div>

	<div class="loginbody">
		<div style="width:100%;height:71px;margin-top:75px"></div>
		<form action="loginAction" class="loginbox" method="post" enctype="multipart/form-data">
			<div class="div-login">
				<div class="input-prepend">
					<span class="add-on"><img src="img/login1.png"/></span> 
					<input class="span2" name="user.schNum"
						id="inputName" type="text" placeholder="Username">
				</div>
				<div class="input-prepend">
					<span class="add-on"><img src="img/login2.png"/></span> 
					<input class="span2" name="user.pwd"
						id="inputPassword" type="password" placeholder="password">
				</div>
				<input type="text" name="identity" value="admin" style="display:none">
				<div class="div-btn"><input type="submit" class="loginbtn" value="登录" onclick="return isNull()"/> <input
					type="reset" name="reset" class="loginbtn" value="重置" /><a
					href="#">忘记密码？</a></div>
			</div>
		</form>

	</div>

	<div class="loginbm">Copyright &copy; 2015 Xidian University,All
		Rights Reserved</div>
</body>

</html>
