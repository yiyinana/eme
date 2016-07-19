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

<title>修改课程信息3</title>

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
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function deleteContent(label) {
		var div = label.parentNode.parentNode;
		div.remove();
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}
	function addFirstContent() {
		//拼出一个章节添加的section
		var h5 = document.createElement("h5");
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>章节名称：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>支撑目标：</label><div class='controls'><s:iterator value='targets' var='t' status='s'><label class='label-checkbox'><input class='checkbox' type='checkbox' value='教学目标<s:property value='#s.index+1'/>'>教学目标<s:property value='#s.index+1' /></label></s:iterator><input type='text' class='hidden' value='<s:property value='#c.cscTarget' />'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>学习目的：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "control-group");
		div5.innerHTML += "<label class='control-label'>基本要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div6 = document.createElement("div");
		div6.setAttribute("class", "control-group");
		div6.innerHTML += "<label class='control-label'>学习重点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div7 = document.createElement("div");
		div7.setAttribute("class", "control-group");
		div7.innerHTML += "<label class='control-label'>学习难点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div8 = document.createElement("div");
		div8.setAttribute("class", "control-group");
		div8.innerHTML += "<label class='control-label'>课外作业及要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div9 = document.createElement("div");
		div9.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div9.appendChild(label1);
		div9.appendChild(label2);
		var hr = document.createElement("hr");

		var section = document.createElement("section");
		section.appendChild(h5);
		section.appendChild(div1);
		section.appendChild(div2);
		section.appendChild(div3);
		section.appendChild(div4);
		section.appendChild(div5);
		section.appendChild(div6);
		section.appendChild(div7);
		section.appendChild(div8);
		section.appendChild(div9);
		section.appendChild(hr);

		var div = document.getElementById("div-content");
		div.appendChild(section);
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}
	function addContent(label) {
		//拼出一个章节添加的section
		var h5 = document.createElement("h5");
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>章节名称：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>支撑目标：</label><div class='controls'><s:iterator value='targets' var='t' status='s'><label class='label-checkbox'><input class='checkbox' type='checkbox' value='教学目标<s:property value='#s.index+1'/>'>教学目标<s:property value='#s.index+1' /></label></s:iterator><input type='text' class='hidden' value='<s:property value='#c.cscTarget' />'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>学习目的：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "control-group");
		div5.innerHTML += "<label class='control-label'>基本要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div6 = document.createElement("div");
		div6.setAttribute("class", "control-group");
		div6.innerHTML += "<label class='control-label'>学习重点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div7 = document.createElement("div");
		div7.setAttribute("class", "control-group");
		div7.innerHTML += "<label class='control-label'>学习难点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div8 = document.createElement("div");
		div8.setAttribute("class", "control-group");
		div8.innerHTML += "<label class='control-label'>课外作业及要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div9 = document.createElement("div");
		div9.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div9.appendChild(label1);
		div9.appendChild(label2);
		var hr = document.createElement("hr");

		var nextSection = document.createElement("section");
		nextSection.appendChild(h5);
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(div6);
		nextSection.appendChild(div7);
		nextSection.appendChild(div8);
		nextSection.appendChild(div9);
		nextSection.appendChild(hr);

		var section = label.parentNode.parentNode;//获取作为参数传进来的label的父section
		if (section.nextSibling) {//如果父section后面还有section，则在后面那个section之前插入新建的section
			section.parentNode.insertBefore(nextSection, section.nextSibling);

		} else {//否则直接把新建的section插在最后面
			section.parentNode.appendChild(nextSection);
		}
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}

	function addName() {//提交表单之前为每一个input添加name属性
		var input = document.getElementsByClassName("input-long");
		var checkbox = document.getElementsByClassName("checkbox");
		var n = checkbox.length/(input.length / 7);//该课程有几个目标
		for (var j = 0; j < (input.length / 7); j++) {
			input[j * 7 + 0].setAttribute("name", "csc[" + j + "].cscChapter");
			input[j * 7 + 1]
					.setAttribute("name", "csc[" + j + "].cscClassHour");
			for(var i=0;i<n;i++){
					checkbox[j * n + i].setAttribute("name", "csc[" + j + "].cscTarget");
			}
			
			input[j * 7 + 2].setAttribute("name", "csc[" + j + "].cscGoal");
			input[j * 7 + 3].setAttribute("name", "csc[" + j + "].cscBasRequ");
			input[j * 7 + 4].setAttribute("name", "csc[" + j
					+ "].cscStudyEmpha");
			input[j * 7 + 5].setAttribute("name", "csc[" + j
					+ "].cscStudyDiffi");
			input[j * 7 + 6].setAttribute("name", "csc[" + j + "].cscHomework");
		}
	}
	
	$(function(){
		var input = document.getElementsByClassName("autoSelect");
		for(var i=0;i<input.length;i++){
			var value=input[i].value;
			var checkbox = input[i].parentNode.getElementsByClassName("checkbox");
			var arg = [];
			arg = value.split(",");
			for(var j=0;j<checkbox.length;j++){
				for(var k=0;k<arg.length;k++){
					console.log(checkbox[j].value.trim()+"  "+arg[k].trim());
					if(checkbox[j].value.trim() == arg[k].trim()){
						checkbox[j].setAttribute("checked","true");
					}
				}
			}
		}
	})
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
				<form action="TeacherCourse_Modify_3_modifySpcConByCursId"
					method="post" class="form-horizontal">
					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-content">
							<s:iterator value="csc" var="c" status="s">
								<section>
									<h5>
										第
										<s:property value="#s.index+1" />
										章
									</h5>
									<div class="control-group">
										<label class="control-label">章节名称：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscChapter" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学时：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscClassHour" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">支撑目标：</label>
										<div class="controls">
												<s:iterator value="targets" var="t" status="s">
													<label class='label-checkbox'><input class="checkbox" type="checkbox" value="教学目标<s:property value="#s.index+1"/>">教学目标
														<s:property value="#s.index+1" /></label>
												</s:iterator>
											<input type="text" class="hidden autoSelect"
												value="<s:property value="#c.cscTarget" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习目的：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscGoal" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">基本要求：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscBasRequ" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习重点：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscStudyEmpha" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习难点：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscStudyDiffi" />">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">课外作业及要求：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="<s:property value="#c.cscHomework" />">
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
						<div class="div-btn">
							<label class="btn" onclick="addFirstContent()">添加</label> <input
								onclick="addName()" type="submit" value="提交" class="btn">
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>

	<script src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
