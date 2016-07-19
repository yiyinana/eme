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

<title>添加课程信息1</title>

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
	function isEmpty(){
		var inputs = document.getElementsByTagName("input");
		for(var i=0;i<inputs.length;i++){
			var value=inputs[i].value;
			if(value==""){
				alert("输入框不能为空");
				inputs[i].focus();
				return false;
			}
		}
		var hour = document.getElementById("input-hour").value;
		var credit = document.getElementById("input-credit").value;
		var errorStr = "";
		if(isNaN(hour)){
			errorStr += "学时，";
		}
		if(isNaN(credit)){
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
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			
			<div class="span10">
			<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminCourse_List_1_selectAllCurs">课程信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>添加课程基本信息
					</h6>
				</div>
				<form action="AdminCourse_List_1_addCursBasicInf" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					
					<div class="div-inf">

						<div class="div-inf-title">基本信息</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程编号：</label>
							<div class="controls">
								<input type="text" name="course.cursNum"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程名称：</label>
							<div class="controls">
								<input type="text" name="course.cursName"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<input type="text" name="course.cursEngName"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学时：</label>
							<div class="controls">
								<input type="text" name="course.cursClassHour" id="input-hour">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学分：</label>
							<div class="controls">
								<input type="text" name="course.cursCredit" id="input-credit">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程性质：</label>
							<div class="controls">
								<select name="course.cursProperty">
									<option value="公共基础课">公共基础课</option>
									<option value="学科基础课">学科基础课</option>
									<option value="专业核心课">专业核心课</option>
									<option value="专业选修课">专业选修课</option>
									<option value="实习、实践类课程">实习、实践类课程</option>
									<option value="基础素质培养课">基础素质培养课</option>
								</select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">适用专业：</label>
							<div class="controls">
								<input type="text" name="course.cursApplMajor"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开设学期：</label>
							<div class="controls">
								<input type="text" name="course.cursTerm">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">先修课程：</label>
							<div class="controls">
								<input type="text" name="course.cursPreCourses"<%-- value="<s:property value="teacher.tchrTitle"/>" --%>>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开课院系：</label>
							<div class="controls">
								<select name="course.dept.deptId">
									<s:iterator value="departments" var="d">
										<option value="<s:property value="#d.deptId" />"><s:property value="#d.deptName" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程负责人：</label>
							<div class="controls">
								<select name="course.teacher.tchrId">
									<s:iterator value="teachers" var="t">
										<option value="<s:property value="#t.tchrId" />"><s:property value="#t.tchrSchNum" /><s:property value="#t.tchrName" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">课程简介</div>
						<div class="div-inner-text">
							<textarea name="course.cursIntro"></textarea>
						</div>
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="添加">
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
