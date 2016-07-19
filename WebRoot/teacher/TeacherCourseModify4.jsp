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

<title>修改课程信息4</title>

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
<%request.removeAttribute("Message");%> //显示后将request里的Message清空，防止回退时重复显示。
	function deleteContent(label) {
		var section = label.parentNode.parentNode;
		section.remove();
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	function addFirstContent() {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label class='label-index'></label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label1);
		div5.appendChild(label2);
		var hr = document.createElement("hr");
		
		var section = document.createElement("section");
		section.appendChild(div1);
		section.appendChild(div2);
		section.appendChild(div3);
		section.appendChild(div4);
		section.appendChild(div5);
		section.appendChild(hr);
		
		var div = document.getElementById("div-method");
		div.appendChild(section);
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	function addContent(label) {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label class='label-index'></label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label1);
		div5.appendChild(label2);
		var hr = document.createElement("hr");
		
		var nextSection = document.createElement("section");
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(hr);
		
		var section = label.parentNode.parentNode;//获取作为参数传进来的label的父section
		if(section.nextSibling){//如果父section后面还有section，则在后面那个section之前插入新建的section
			section.parentNode.insertBefore(nextSection,section.nextSibling);
			}else{//否则直接把新建的section插在最后面
				section.parentNode.appendChild(nextSection);
			}
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	
	function addName(){//提交表单之前为每一个input添加name属性
		var input = document.getElementsByClassName("input-long");
		for(var j=0;j<(input.length/3);j++){
			input[j*3+0].setAttribute("name","ctm["+j+"].cursContent");
			input[j*3+1].setAttribute("name","ctm["+j+"].period");
			input[j*3+2].setAttribute("name","ctm["+j+"].teacMethod");
		}
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
				<form action="TeacherCourse_Modify_4_modifyTchModeByCursId" method="post"
					class="form-horizontal">

					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-method">
							<s:iterator value="ctm" var="c" status="s">
								<section>
									<div class="control-group">
										<label class="control-label">序号：</label>
										<div class="controls">
											<label class="label-index"><s:property value="#s.index+1" /></label>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">课程内容：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#c.cursContent" />" class="input-long">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学时：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#c.period" />" class="input-long">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">教学方式：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#c.teacMethod" />" class="input-long">
										</div>
									</div>
									
									<div class="div-btn">
										<label class="btn" onclick="addContent(this)">添加</label> <label
											class="btn" onclick="deleteContent(this)">删除</label>
									</div>
									<hr>
								</section>
							</s:iterator>
						</div>
						<div class="div-btn"><label class="btn" onclick="addFirstContent(this)">添加</label>
							<input onclick="addName()" type="submit" value="提交" class="btn">
						</div>
					</div>
				</form>
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
