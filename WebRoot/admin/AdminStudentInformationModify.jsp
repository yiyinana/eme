<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>修改学生信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	$(function(){
		var selectedClaId = $("#hiddenClazz").attr("value");
		var selectedGenId = $("#hiddenGender").attr("value");
		$("#"+selectedClaId).attr("selected",true);
		$("#"+selectedGenId).attr("selected",true);
	});
	function check(form) {
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			return true;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "img" && postf != "png" && postf != "jpg") {
			alert("您选择的文件格式不正确！");
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminStudent_List_1_selectAllStus">学生信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>修改学生信息
					</h6>
				</div>
				<!-- 教师基本信息 -->
				<div class="div-tchr-basic-inf">
					<form action="AdminStudent_Information_1_modifyBySchNum" method="post"
					class="form-horizontal form-add" enctype="multipart/form-data">
					<div class="control-group">
						<label class="control-label">姓名：</label>
						<div class="controls">
							<input type="text" name="s.stuName" value="<s:property value="s.stuName"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学号：</label>
						<div class="controls">
							<input type="text" name="s.stuSchNum" value="<s:property value="s.stuSchNum"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
						<input value="<s:property value="s.stuGender" />" style="display:none" id="hiddenGender"/>
							<select name="s.stuGender">
								<option id="true" value="true">男</option>
								<option id="false" value="false">女</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">民族：</label>
						<div class="controls">
							<input type="text" name="s.stuNation" value="<s:property value="s.stuNation"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">入学日期：</label>
						<div class="controls">
							<input type="text" name="s.stuAttendDate" value="<s:property value="s.stuAttendDate"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">班级：</label>
						<div class="controls">
						<input value="<s:property value="s.clazz.claId" />" style="display:none" id="hiddenClazz"/>
							<select name="clazzId">
							<s:iterator value="allClazz" var="c">
								<option id="<s:property value="#c.claId" />" value="<s:property value="#c.claId" />"><s:property value="#c.claName" /></option>
								</s:iterator>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学制：</label>
						<div class="controls">
							<input type="text" name="s.stuSchLength" value="<s:property value="s.stuSchLength"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">证件号码：</label>
						<div class="controls">
							<input type="text" name="s.stuIdentNum" value="<s:property value="s.stuIdentNum"/>">
						</div>
					</div>
					<!-- 上传头像 -->
					<div class="control-group">
						<label class="control-label">上传头像：</label>
						<div class="controls">
							<a class="btn btn-file">选择文件<input type="file" name="file"
								id="filename"></a><input name="uploadUrl" value="/stuImg"
								style="display:none" />
						</div>
					</div>
					<!-- 上传头像 -->
					<div class="margin12">
						<input type="submit" value="提交" class="btn"
							onclick="return check(this.form)">
					</div>
				</form>
				</div>
				<!-- 教师基本信息完 -->
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
