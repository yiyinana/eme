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
<title>My JSP 'LoginFrame.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" href="css/common.css" />
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="css/login.css" />

<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
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

<body>
	<div class="row-fluid">
		<div class="span7">
			<img class="schoolLogoImg" alt="logo" src="img/homelogo.png">
		</div>
		<div class="span5">
			<img class="sysNameImg" alt="logo" src="img/sysName.png">
		</div>
	</div>

	<div class="row-fluid">
		<!--左边-->
		<div class="span8" id="leftDiv">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab1" data-toggle="tab">专业介绍</a>
					</li>
					<li><a href="#tab2" data-toggle="tab">培养目标</a></li>
					<li><a href="#tab3" data-toggle="tab">课程体系</a></li>
					<li><a href="#tab4" data-toggle="tab">毕业要求</a></li>
					<li><a href="#tab5" data-toggle="tab">达成度</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
					</div>
					<div class="tab-pane" id="tab2">
						<p>
						</p>
					</div>
					<div class="tab-pane" id="tab3">
						<p>Beef ribs, turducken ham hock...</p>
					</div>
					<div class="tab-pane" id="tab4">
						<p>Beef ribs</p>
					</div>
					<div class="tab-pane" id="tab5">
						<p>turducken ham hock...</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 右边登录窗口 -->
		<div class="span4" id="rightDiv">
			<div class="row">
				<div class="span8 offset3" id="loginDiv">
					<div class="loginLblDiv">
						<label class="loginLbl">用户登录</label>
					</div>
					<form class="form-horizontal" id="loginForm" name="loginForm"
						action="loginAction" onsubmit="javascript:return isNull()">
						<div class="control-group">
							<label class="control-label" for="inputName">用户名：</label>
							<div class="controls">
								<input class="small-input" type="text" id="inputName"
									name="user.schNum" placeholder="用户名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">密&nbsp;&nbsp;&nbsp;码：</label>
							<div class="controls">
								<input class="small-input" type="password" id="inputPassword"
									name="user.password" placeholder="密码">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" class="btn" value="登录"> <input
									type="submit" class="btn" value="忘记密码">
							</div>
						</div>
					</form>
					<hr />
					<div class="loginNoteDiv">
						<p>
							如提示“账号”或“密码”错误，请填写账号、姓名、电话、身份证号、发邮件到信息处邮箱。
							<br>
						<address>信息处邮箱：xdxxc@mail.xidian.edu.cn</address>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>
