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

<title>教师教学管理5</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	$(function() {
		var $termSelect = $("#year");
		var d = new Date();
		var year = d.getFullYear();//获取当前年份
		
		var $Option2 = $("<option value='" + (year - 3) + "'>" + (year - 3)
				+ "</option>");
		var $Option3 = $("<option value='" + (year - 2) + "'>" + (year - 2)
				+ "</option>");
		var $Option4 = $("<option value='" + (year - 1) + "'>" + (year - 1)
				+ "</option>");
		var $Option5 = $("<option value='" + (year) + "'>" + (year)
				+ "</option>");
		var $Option6 = $("<option value='" + (year + 1) + "'>" + (year + 1)
				+ "</option>");
		var $Option7 = $("<option value='" + (year + 2) + "'>" + (year + 2)
				+ "</option>");
		var $Option8 = $("<option value='" + (year + 3) + "'>" + (year + 3)
				+ "</option>");
		var $Option9 = $("<option value='" + (year + 4) + "'>" + (year + 4)
				+ "</option>");
		$termSelect.append($Option2).append($Option3).append(
				$Option4).append($Option5).append($Option6).append($Option7)
				.append($Option8).append($Option9);

	});
	
	function postwith(to,p){//让超链接a以post方式提交，避免中文乱码
		var myForm = document.createElement("form");
		myForm.method = "post";
		myForm.action = to;
		
		for(var k in p){
			var input = document.createElement("input");
			input.setAttribute("name", k);
			input.setAttribute("value", p[k]);
			myForm.appendChild(input);
		}
		
		document.body.appendChild(myForm);
		myForm.submit();
		document.body.removeChild(myForm);
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<!-- 成绩评定与查询 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>达成度查询</label>
								</div>
								<div class="div-inf-tbl">
									<div class="div-result-import">
									<h5>课程达成评价</h5>
										<form action="Teacher_Management_5_calculateClazzCursTarget" method="post">
											<div class="div-select">
												班级&nbsp;&nbsp;<select id="class" name="clazzName">
													<s:iterator value="allClazz" var="clazz">
														<option value="<s:property value="#clazz.claName"/>"><s:property
																value="#clazz.claName" /></option>
													</s:iterator>
												</select>
											</div>
											<div class="div-select">
												课程&nbsp;&nbsp;<select id="course" name="cursName">
													<s:iterator value="tchrCourses" var="c">
														<option value="<s:property value="#c.cursName"/>"><s:property
																value="#c.cursName" /></option>
													</s:iterator>
												</select>
											</div>
											<input class="btn" type="submit" value="计算达成度">
										</form>
										<h5>课程达成度查询</h5>
										<form action="Teacher_Management_5_findB1B2" method="post">
											<div class="div-select-short">
												年级&nbsp;&nbsp;<select id="year" name="grade">
												</select>
											</div>
											<div class="div-select">
												课程&nbsp;&nbsp;<select id="course" name="cursName">
													<s:iterator value="tchrCourses" var="c">
														<option value="<s:property value="#c.cursName"/>"><s:property
																value="#c.cursName" /></option>
													</s:iterator>
												</select>
											</div>
											<input class="btn" type="submit" value="查询">
										</form>
									</div>
									<h6 class="h-target">课程教学目标达成度</h6>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>班级</th>
												<s:iterator value="targets" var="t" status="s">
													<th>目标<s:property value="#s.index+1" />
													</th>
												</s:iterator>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="b1" var="b1s">
												<tr>
													<s:iterator value="#b1s" var="b1l">
														<td><s:property value="#b1l" /></td>
													</s:iterator>
													<td><a href="javascript:postwith('Teacher_PointValue_Detail_getClaCursTargetDetail',{'clazzName':'<s:property value="#b1s[0]"/>'})">详细</a></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<h6 class="h-target">课程对毕业要求达成度</h6>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>班级</th>
												<s:iterator value="points" var="p">
													<th>指标点<s:property value="#p" />
													</th>
												</s:iterator>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="b2" var="b2s">
												<tr>
													<s:iterator value="#b2s" var="b2l">
														<td><s:property value="#b2l" /></td>
													</s:iterator>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>

							</div>
							<!-- 成绩评定与查询完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
