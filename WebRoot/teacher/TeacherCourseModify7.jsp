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

<title>修改课程信息7</title>

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
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function deleteContent(label) {
		var section = label.parentNode.parentNode;
		section.remove();
	}
	function addMaterialContent() {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>作者：</label><div class='controls'><input type='text' class='input-long input-m'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>书名：</label><div class='controls'><input type='text' class='input-long input-m'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>出版社：</label><div class='controls'><input type='text' class='input-long input-m'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>出版年份：</label><div class='controls'><input type='text' class='input-long input-m'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label2);
		var hr = document.createElement("hr");

		var nextSection = document.createElement("section");
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(hr);

		var div = document.getElementById("material");
		div.appendChild(nextSection);
	}
	function addRefBookContent() {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>作者：</label><div class='controls'><input type='text' class='input-long input-rb'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>书名：</label><div class='controls'><input type='text' class='input-long input-rb'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>出版社：</label><div class='controls'><input type='text' class='input-long input-rb'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>出版年份：</label><div class='controls'><input type='text' class='input-long input-rb'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label2);
		var hr = document.createElement("hr");

		var nextSection = document.createElement("section");
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(hr);

		var div = document.getElementById("refBook");
		div.appendChild(nextSection);
	}
	function addName() {//提交表单之前为每一个input添加name属性
		var input1 = document.getElementsByClassName("input-m");
		for (var i = 0; i < (input1.length / 4); i++) {
			input1[i * 4 + 0].setAttribute("name", "cm[" + i + "].cmAuthor");
			input1[i * 4 + 1].setAttribute("name", "cm[" + i + "].cmName");
			input1[i * 4 + 2].setAttribute("name", "cm[" + i + "].cmPublisher");
			input1[i * 4 + 3].setAttribute("name", "cm[" + i + "].cmPubYear");
		}
		var input2 = document.getElementsByClassName("input-rb");
		for (var j = 0; j < (input2.length / 4); j++) {
			input2[j * 4 + 0].setAttribute("name", "crb[" + j + "].crbAuthor");
			input2[j * 4 + 1].setAttribute("name", "crb[" + j + "].crbName");
			input2[j * 4 + 2].setAttribute("name", "crb[" + j
					+ "].crbPublisher");
			input2[j * 4 + 3].setAttribute("name", "crb[" + j + "].crbPubYear");
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
					<h6>
						<img class="image-path-1" src="img/circle.jpg" /> <a
							href="Teacher_Management_2_selectTchrCourse">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<form action="TeacherCourse_Modify_7_modifyBookByCursId"
					method="post" class="form-horizontal">
					<div class="div-inf">
						<div class="div-inf-title">
							课程教材<label class="a-add" onclick="addMaterialContent()">添加</label>
						</div>
						<div id="material">
							<s:iterator value="cm" var="m">
								<section>
									<div class="control-group">
										<label class="control-label">作者：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#m.cmAuthor" />"
												class="input-long input-m">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">书名：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#m.cmName" />"
												class="input-long input-m">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">出版社：</label>
										<div class="controls">
											<input type="text"
												value="<s:property value="#m.cmPublisher" />"
												class="input-long input-m">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">出版年份：</label>
										<div class="controls">
											<input type="text"
												value="<s:property value="#m.cmPubYear" />"
												class="input-long input-m">
										</div>
									</div>
									<div class="div-btn">
										<label class="btn" onclick="deleteContent(this)">删除</label>
									</div>
									<hr>
								</section>
							</s:iterator>
						</div>
						<!-- 课程参考书目 -->
						<div class="div-inf-title">
							参考书目<label class="a-add" onclick="addRefBookContent()">添加</label>
						</div>
						<div id="refBook">
							<s:iterator value="crb" var="c">
								<section>
									<div class="control-group">
										<label class="control-label">作者：</label>
										<div class="controls">
											<input type="text"
												value="<s:property value="#c.crbAuthor" />"
												class="input-long input-rb">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">书名：</label>
										<div class="controls">
											<input type="text" value="<s:property value="#c.crbName" />"
												class="input-long input-rb">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">出版社：</label>
										<div class="controls">
											<input type="text"
												value="<s:property value="#c.crbPublisher" />"
												class="input-long input-rb">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">出版年份：</label>
										<div class="controls">
											<input type="text"
												value="<s:property value="#c.crbPubYear" />"
												class="input-long input-rb">
										</div>
									</div>
									<div class="div-btn">
										<label class="btn" onclick="deleteContent(this)">删除</label>
									</div>
									<hr>
								</section>
							</s:iterator>
						</div>
					</div>
					<div class="div-btn">
						<input onclick="addName()" type="submit" value="提交" class="btn">
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
