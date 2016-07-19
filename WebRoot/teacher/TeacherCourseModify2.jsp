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

<title>修改课程信息2</title>

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
		number();
	});
	
	function number(){
		var label = document.getElementsByClassName("control-label");
		for(var i=0;i<label.length;i++){
			label[i].innerHTML = "教学目标"+(i+1)+":";
		}
	}
	
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
	}
	
	function deleteDiv(label){
		var div = label.parentNode.parentNode;
		div.remove();
		number();
	}
	
	function addName(){
		var inputs = document.getElementsByClassName("target");
		for(var i=0;i<inputs.length;i++){
			inputs[i].setAttribute("name","targets["+i+"].tchTarContent");
		}
	}
	
	function addTarget(){
		var div = document.createElement("div");
		div.setAttribute("class", "control-group");
		div.innerHTML += "<label class='control-label'></label><div class='controls'><input type='text' class='input-long target'><label class='label-delete' onclick='deleteDiv(this)'>删除</label></div>";
		document.getElementById("div-targets").appendChild(div);
		number();
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/include/course-modify-left.jsp"%>
			<div class="span10">
			<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="Teacher_Management_2_selectTchrCourse">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<form action="TeacherCourse_Modify_2_modifyTargetByCursId" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					<div class="div-inf" id="div-targets">
					<s:iterator value="targets" var="t" status="s">
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input type="text" class="input-long target"
								value="<s:property value="#t.tchTarContent"/>" /><label class="label-delete" onclick="deleteDiv(this)">删除</label>
							</div>
						</div>
						</s:iterator>
					</div>
					<div class="div-btn">
						<label class="btn" onclick="addTarget()">添加</label>
						<input type="submit" class="btn" onclick="addName()" value="确定">
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
