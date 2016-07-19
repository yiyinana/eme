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

<title>学生成绩查询</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_information.css" />
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
		var $Option1 = $("<option value='" + (year - 4) + "'>" + (year - 4)
				+ "</option>");
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
		$termSelect.append($Option1).append($Option2).append($Option3).append(
				$Option4).append($Option5).append($Option6).append($Option7)
				.append($Option8).append($Option9);

	});
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/student_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/stuLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<div class="div-inf-bar">
								<label>学生成绩查询</label>
							</div>
							<div class="div-inf-tbl">
								<form action="Student_Performance_1_selectStuPer" method="post"
									enctype="multipart/form-data">
									<h5>
										<s:property value="currentTerm" />
									</h5>
									<div class="div-select-short">
										年份&nbsp;&nbsp;<select id="year" name="year">
											<!-- <option value="" selected="selected">全部</option> -->
										</select>
									</div>
									<div class="div-select-short">
										学期&nbsp;&nbsp;<select id="term" name="term">
											<!-- <option value="" selected="selected">全部</option> -->
											<option value="春">春</option>
											<option value="秋">秋</option>
										</select>
									</div>
									<%-- <div class="div-select">
										课程&nbsp;&nbsp;<select id="course" name="stuCursLimit.cursName">
											<s:iterator value="tchrCourses" var="c">
												<option value="<s:property value="#c.cursName"/>"><s:property
														value="#c.cursName" /></option>
											</s:iterator>
										</select>
									</div> --%>

									<input class="btn" type="submit" value="查询成绩">
								</form>
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>课程编号</th>
											<th>课程名称</th>
											<th>课程性质</th>
											<th>学分</th>
											<th>成绩</th>
											<th>开课学院</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="stuCurs" var="p">
											<tr>
												<td><s:property value="#p.course.cursNum" /></td>
												<td><s:property value="#p.course.cursName" /></td>
												<td><s:property value="#p.course.cursProperty" /></td>
												<td><s:property value="#p.course.cursCredit" /></td>
												<td><s:property value="#p.EvaValue" /></td>
												<td><s:property value="#p.course.dept.deptName" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>

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
