<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>学生获奖内容</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_goal.css" />
<link rel="stylesheet" href="css/student_item_file.css" />
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
						<div class="span9 div-content-white-bgr" style="min-height: 440px">
							<div class="div-inf-bar">
								<label>学生成绩</label>
							</div>
							<div class="div-inf-tbl">
								<div>
									<span class="text-size left-distance">查询学年：</span><input
										type="text" name="startSchoolYear" id="startSchoolYear"
										style="width: 40px" onchange="changeEndYear()"
										onFocus="WdatePicker(WdatePicker({lang:'zh-cn',dateFmt:'yyyy',readOnly:'true'})) ">
									<span class="text-size ">至</span>&nbsp;&nbsp;<input type="text"
										name="endSchoolYear" id="endSchoolYear" style="width: 40px"
										readOnly> <input type="button" id="evaSummary"
										class="btn left-distance" value="查询成绩"
										onclick="selectCourseGrades()">
								</div>
								<div class="div-tchr-basic-inf">
									
										<table class="table table-bordered " id="stuCourseGradeList" style="width: 100%">
											<thead>
												<tr>
													<th style="width: 60px">课程编号</th>
													<th>课程名称</th>
													<th>学分</th>
													<th>学时</th>
													<th>学科性质</th>
													<th>授课教师</th>
													<th>成绩</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="studentCourses" var="sc">
													<tr>
														<td><s:property value="#sc.course.cursNum" /></td>
														<td><s:property value="#sc.course.cursName" /></td>
														<td><s:property value="#sc.course.cursCredit" /></td>
														<td><s:property value="#sc.course.cursClassHour" /></td>
														<td><s:property value="#sc.course.cursProperty" /></td>
														<td><s:property value="#sc.course.teacher.tchrName" /></td>
														<td><s:property value="#sc.EvaValue" /></td>
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
		</div>


	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script src="js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>

	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});

		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		//当选择学年开始时间之后给定结束时间
		function changeEndYear() {
			var startSchoolYear = $("#startSchoolYear").val();
			document.getElementById("endSchoolYear").value = parseInt(startSchoolYear) + 1;
		}
		function selectCourseGrades() {
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			$("#stuCourseGradeList tbody").html("");
			$.getJSON("Json_selectStuCourseGrades", {
				schoolYear : schoolYear
			}, function(data) {
				$.each(data.studentCourses, function(i, value) {

					$("#stuCourseGradeList").append(
							"<tr><td>" + value.course.cursNum + "</td><td>"
									+ value.course.cursName + "</td><td>"
									+ value.course.cursCredit + "</td><td>"
									+ value.course.cursClassHour + "</td><td>"
									+ value.course.cursProperty + "</td><td>"
									+ value.course.teacher.tchrName
									+ "</td><td>" + value.evaValue
									+ "</td></tr>");
				});
			});
		}
	</script>

</body>
</html>
