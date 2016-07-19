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

<title>专业信息设置</title>

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
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	
	function isEmpty() {
		var majorInfo = document.getElementById("input-info").value.toString()
				.trim().length;
		var target = document.getElementById("input-target").value.toString()
				.trim().length;

		if (majorInfo != 0 && target != 0) {
			return true;
		} else {
			alert("输入框不能为空");
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
						<a href="admin/AdminHome1.jsp">首页</a>>专业信息设置
					</h4>
					<hr />
				</div>
				<div class="main">
					<div class="div-inf">
					<label class="red">当前专业负责人:
						<s:iterator value="managers" var="m">
								<s:property value="#m.tchrSchNum" />
								<s:property value="#m.tchrName" />/
						</s:iterator>
					</label>
					<h6>指定专业负责人</h6>
					<form action="Admin_MajorInformation_Modify_addMajorManager" method="post" class="form-horizontal form-add">
						<s:iterator value="teachers" var="t">
							<div class="div-tchr-name-list">
								<input type="checkbox" value="<s:property value="#t.tchrId" />" name="managerId" />
								<s:property value="#t.tchrSchNum" />
								<s:property value="#t.tchrName" />
							</div>
						</s:iterator>
						<div class="div-btn">
							<input type="submit" value="确定" class="btn">
						</div>
					</form>
					<hr>
				</div>
				<div class="div-inf">
						<h6>设置专业信息</h6>
						<form action="Admin_MajorInformation_Modify_updateMajorInfo"
							method="post" class="form-horizontal form-add"
							enctype="multipart/form-data"
							onsubmit="javascript:return isEmpty(1)">
							<div class="div-inf">
								<label>专业信息</label> <br> <br>
								<textarea id="input-info" name="majorInformation.majorIntr"
									class="textarea"><s:property
										value="majorInformation.majorIntr" /></textarea>
								<br> <br> <label>培养目标</label> <br> <br>
								<textarea id="input-target" name="majorInformation.eduTarget"
									class="textarea"><s:property
										value="majorInformation.eduTarget" /></textarea>
							</div>
							<br>
							<div class="div-btn">
								<input type="submit" value="提  交" class="btn">
							</div>
						</form>
						<hr>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
