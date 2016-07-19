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

<title>添加学生</title>

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
	function check(form) {
		var schNum = document.getElementById("input-number").value;
		var name = document.getElementById("input-name").value;
		var identify = document.getElementById("input-identify").value;
		var errorStr1 = "";
		var errorStr2 = "";
		if(schNum==""){
			errorStr1 += "学号";
		}
		if(name==""){
			errorStr1 += "姓名";
		}
		if(identify.length!=18){
			errorStr2 += "请输入18位身份证号。";
		}
		
		if(errorStr1!=""){
			alert(errorStr1+"不能为空！");
			return false;
		}
		if(errorStr2!=""){
			alert(errorStr2);
			return false;
		}
		
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			alert("请选择头像！");
			return false;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "jpg") {
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
							href="AdminStudent_List_1_selectAllStus">学生信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>新增学生信息
					</h6>
				</div>
				<form action="AdminStudent_Add_1_addStudent" method="post"
					class="form-horizontal form-add" enctype="multipart/form-data">
					<div class="control-group">
						<label class="control-label">姓名：</label>
						<div class="controls">
							<input type="text" name="s.stuName"  id="input-name">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学号：</label>
						<div class="controls">
							<input type="text" name="s.stuSchNum" id="input-number">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
							<select name="s.stuGender">
								<option value="true">男</option>
								<option value="false">女</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">民族：</label>
						<div class="controls">
							<input type="text" name="s.stuNation"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">入学日期：</label>
						<div class="controls">
							<input type="date" name="s.stuAttendDate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">班级：</label>
						<div class="controls">
							<select name="clazzId">
							<s:iterator value="allClazz" var="c">
								<option value="<s:property value="#c.claId" />"><s:property value="#c.claName" /></option>
								</s:iterator>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学制：</label>
						<div class="controls">
							<input type="text" name="s.stuSchLength"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">证件号码：</label>
						<div class="controls">
							<input type="text" name="s.stuIdentNum" id="input-identify">
						</div>
					</div>
					<!-- 上传头像 -->
					<div class="control-group">
						<label class="control-label">上传头像：</label>
						<div class="controls">
							<a class="btn btn-file">选择文件<input type="file" name="file"
								id="filename"></a><input name="uploadUrl" value="/stuImg"
								class="hidden" />
						</div>
					</div>
					<!-- 上传头像 -->
					<div class="margin12">
						<input type="submit" value="添加" class="btn"
							onclick="return check(this.form)">
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
