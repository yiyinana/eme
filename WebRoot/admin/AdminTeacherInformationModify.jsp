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

<title>修改教师信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
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
							href="AdminTeacher_List_1_selectAllTchrs">教师信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>修改教师信息
					</h6>
				</div>
				<!-- 教师基本信息 -->
				<div class="div-tchr-basic-inf">
					<form class="form-horizontal form-add"
						action="AdminTeacher_Information_1_modifyBySchNum" method="post"
						enctype="multipart/form-data">
						<div class="control-group">
							<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
							<div class="controls">
								<input type="text" name="teacher.tchrName" value="<s:property value="teacher.tchrName"/>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
							<div class="controls">
								<input type="text" name="teacher.tchrSchNum" value="<s:property value="teacher.tchrSchNum"/>">
							</div>
						</div>
						<%-- <div class="control-group">
							<label class="control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
							<div class="controls">
								<select name="teacher.tchrGender">
									<option value="true">男</option>
									<option value="false">女</option>
								</select>
							</div>
						</div> --%>
						<div class="control-group">
						<label class="control-label">出生年月</label>
						<div class="controls">
							<input type="text" name="teacher.tchrBirthday" value="<s:property value="teacher.tchrBirthday"/>">
						</div>
					</div>
						<div class="control-group">
							<label class="control-label">身份证号</label>
							<div class="controls">
								<input type="text" name="teacher.tchrIdentNum" value="<s:property value="teacher.tchrIdentNum"/>">
							</div>
						</div>
						<div class="control-group">
						<label class="control-label">最高学位</label>
						<div class="controls">
							<input type="text" name="teacher.tchrDegree" value="<s:property value="teacher.tchrDegree"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
						<div class="controls">
							<input type="text" name="teacher.tchrTitle" id="title" value="<s:property value="teacher.tchrTitle"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">所学专业</label>
						<div class="controls">
							<input type="text" name="teacher.tchrMajor" value="<s:property value="teacher.tchrMajor"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">毕业院校</label>
						<div class="controls">
							<input type="text" name="teacher.tchrGraduateSchool" value="<s:property value="teacher.tchrGraduateSchool"/>">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">任职时间</label>
						<div class="controls">
							<input type="text" name="teacher.tchrAttendDate" value="<s:property value="teacher.tchrAttendDate"/>">
						</div>
					</div>
						<div class="control-group">
							<label class="control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
							<div class="controls">
								<input type="text" name="teacher.tchrPhone" id="phone" value="<s:property value="teacher.tchrPhone"/>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</label>
							<div class="controls">
								<input type="text" name="teacher.tchrFax" id="fax" value="<s:property value="teacher.tchrFax"/>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电子邮件</label>
							<div class="controls">
								<input type="email" name="teacher.tchrEmail" id="email" value="<s:property value="teacher.tchrEmail"/>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">办&nbsp;&nbsp;公&nbsp;&nbsp;室</label>
							<div class="controls">
								<input type="text" name="teacher.tchrOfficeAddr" id="office" value="<s:property value="teacher.tchrOfficeAddr"/>">
							</div>
						</div>
						<!-- <div class="control-group">
							<label class="control-label">个人主页</label>
							<div class="controls">
								<input type="text" name="" id="self_url" value="www.baidu.com">
							</div>
						</div> -->
						<!-- 上传头像 -->
						<div class="control-group">
							<label class="control-label">上传头像</label>
							<div class="controls">
								<a class="btn btn-file">选择文件<input type="file" name="file"
									id="filename"></a><input name="uploadUrl" value="/tchrImg"
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
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
