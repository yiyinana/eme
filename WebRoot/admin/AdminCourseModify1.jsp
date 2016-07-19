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

<title>修改课程信息1</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<script src="js/jquery1.12.1.js"></script>
<script type="text/javascript">

	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	$(function(){
		var selectedDeptId = $("#department").attr("value");
		$("#d"+selectedDeptId).attr("selected",true);
		
		var selectedTchrId = $("#teacher").attr("value");
		$("#t"+selectedTchrId).attr("selected",true);
	});
	
	function isEmpty(){
		var inputs = document.getElementsByClassName("input-isEmpty");
		for(var i=0;i<inputs.length;i++){
			var value=inputs[i].value;
			if(value==""){
				alert("输入框不能为空");
				inputs[i].focus();
				return false;
			}
		}
		var hour = document.getElementById("input-hour");
		var credit = document.getElementById("input-credit");
		var errorStr = "";
		if(!isNaN(hour)){
			errorStr += "学时，";
		}
		if(!isNaN(credit)){
			errorStr += "学分，";
		}
		
		if(errorStr != ""){
			alert(errorStr+"必须为数字");
			return false;
		}
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10">
			<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="AdminCourse_List_1_selectAllCurs">返回课程列表</a>
					</h6>
				</div>
				<form action="AdminCourse_Modify_1_updateCursBasicInf" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					<div class="div-inf">
						<div class="div-inf-title">基本信息</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程编号：</label>
							<div class="controls">
								<input type="text" name="course.cursNum" value="<s:property value="course.cursNum"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程名称：</label>
							<div class="controls">
								<input type="text" name="course.cursName" value="<s:property value="course.cursName"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<input type="text" name="course.cursEngName" value="<s:property value="course.cursEngName"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学时：</label>
							<div class="controls">
								<input type="text" name="course.cursClassHour" value="<s:property value="course.cursClassHour"/>" id="input-hour" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学分：</label>
							<div class="controls">
								<input type="text" name="course.cursCredit" value="<s:property value="course.cursCredit"/>" id="input-credit" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程性质：</label>
							<div class="controls">
							<input type="text" name="course.cursProperty" value="<s:property value="course.cursProperty"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">适用专业：</label>
							<div class="controls">
								<input type="text" name="course.cursApplMajor" value="<s:property value="course.cursApplMajor"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开设学期：</label>
							<div class="controls">
								<input type="text" name="course.cursTerm" value="<s:property value="course.cursTerm"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">先修课程：</label>
							<div class="controls">
								<input type="text" name="course.cursPreCourses" value="<s:property value="course.cursPreCourses"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开课院系：</label>
							<div class="controls">
							<input id="department" style="display:none" value="<s:property value="course.dept.deptId"/>">
							<select name="course.dept.deptId">
									<s:iterator value="departments" var="d">
										<option id="d<s:property value="#d.deptId"/>" value="<s:property value="#d.deptId" />"><s:property value="#d.deptName" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程负责人：</label>
							<div class="controls">
							<input id="teacher" style="display:none" value="<s:property value="course.teacher.tchrId"/>">
								<select name="course.teacher.tchrId">
									<s:iterator value="teachers" var="t">
										<option id="t<s:property value="#t.tchrId"/>" value="<s:property value="#t.tchrId" />"><s:property value="#t.tchrSchNum" /><s:property value="#t.tchrName" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">课程简介</div>
						<div class="div-inner-text">
							<textarea name="course.cursIntro"><s:property value="course.cursIntro"/></textarea>
						</div>
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="提交">
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
