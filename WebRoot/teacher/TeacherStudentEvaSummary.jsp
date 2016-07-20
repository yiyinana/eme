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

<title>学生列表</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<style type="text/css">
.stu-table {
	width: 600px;
}
</style>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="span9 div-content-white-bgr" style="min-height: 490px">
						<div class="div-inf-bar">
							<label>学生项目评估汇总</label>
						</div>
						<div class="div-inf-tbl">
							<div>
								<div>
									<span class="text-size ">班级：</span>&nbsp;&nbsp;<select
										name="limits.stuClazz" id="stuSchClazz"
										style="width: 140px; height: 30px">
										<option value="" selected="selected">全部</option>
										<s:iterator value="allClazz" var="c">
											<option value="<s:property value="#c.claId"/>"><s:property
													value="#c.claName" /></option>
										</s:iterator>
									</select><br>
								</div>
								<div style="margin-top: 10px">
									<span class="text-size">评定学年：</span><input type="text"
										name="startSchoolYear" id="startSchoolYear"
										style="width: 40px" onchange="changeEndYear(this)"
										onFocus="WdatePicker(WdatePicker({lang:'zh-cn',dateFmt:'yyyy',readOnly:'true'})) ">
									<span class="text-size ">至</span>&nbsp;&nbsp;<input type="text"
										name="endSchoolYear" id="endSchoolYear" style="width: 40px"
										readOnly> <br>
								</div>
								<div style="margin-top: 10px">
									<span class="text-size">提交时间限制：</span> <input type="date"
										pattern="yyyy-MM-dd" style="width: 135px" id="startTime"
										name="startTime"><span class="text-size left-distance">至</span>&nbsp;&nbsp;
									<input type="date" id="endTime" name="endTime"
										pattern="yyyy-MM-dd" style="width: 135px">&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="evaSummary" class="btn left-distance"
										value="评估汇总" onclick="isEmpity(this)">
								</div>
							</div>
						</div>
						<div class="div-inf-tbl" style="margin-top: 100px">
							<div>

								<span class="text-size ">班级：</span>&nbsp;&nbsp;<select
									name="limits.stuClazz" id="stuSchClazz1"
									style="width: 140px; height: 30px">
									<option value="" selected="selected">全部</option>
									<s:iterator value="allClazz" var="c">
										<option value="<s:property value="#c.claId"/>"><s:property
												value="#c.claName" /></option>
									</s:iterator>
								</select><span class="text-size left-distance">评定学年：</span><input
									type="text" name="startSchoolYear1" id="startSchoolYear1"
									style="width: 40px" onchange="changeEndYear(this)"
									onFocus="WdatePicker(WdatePicker({lang:'zh-cn',dateFmt:'yyyy',readOnly:'true'})) ">
								<span class="text-size ">至</span>&nbsp;&nbsp;<input type="text"
									name="endSchoolYear1" id="endSchoolYear1" style="width: 40px"
									readOnly>
							</div>
							<input type="button" value="查看评估结果" class="btn" name="checkEva"
								id="checkEva" onclick="isEmpity(this)"> <input
								type="button" value="导出Excel" class="btn">
							<div class="div-tchr-detail">
								<table class="table table-bordered table-condensed"
									id="evaluateScoreList">
									<thead>
										<tr>
											<th width="40px">学号</th>
											<th width="40px">姓名</th>
											<!-- <th width="40px">学年</th> -->
											<th width="40px">M1</th>
											<th width="40px">M2</th>
											<th width="40px">M3</th>
											<th width="40px">M4</th>
											<th width="40px">M5</th>
											<th width="40px">操作</th>
										</tr>
									</thead>
									<tbody id="evaluateScoreList1">

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
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script src="js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
	<script src="js/stuListPage.js"></script>
	<script type="text/javascript">
		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
		//当选择学年开始时间之后给定结束时间
		function changeEndYear(obj) {
			if (obj.id == "startSchoolYear") {
				var startSchoolYear = $("#startSchoolYear").val();
				document.getElementById("endSchoolYear").value = parseInt(startSchoolYear) + 1;
			} else if (obj.id == "startSchoolYear1") {
				var startSchoolYear = $("#startSchoolYear1").val();
				document.getElementById("endSchoolYear1").value = parseInt(startSchoolYear) + 1;
			}

		}
		//判断评估输入是否为空
		function isEmpity(obj) {
			if (obj.id == "evaSummary") {
				var clazz = $("#stuSchClazz").val();
				var startSchoolYear = $("#startSchoolYear").val();
				var endSchoolYear = $("#endSchoolYear").val();
				if (clazz == "" || startSchoolYear == "" || endSchoolYear == "") {
					alert("请选择班级或学年！");
				} else {
					summaryEva();
				}

			} else if (obj.id == "checkEva") {
				var clazz = $("#stuSchClazz1").val();
				var startSchoolYear = $("#startSchoolYear1").val();
				var endSchoolYear = $("#endSchoolYear1").val();
				if (clazz == "" || startSchoolYear == "" || endSchoolYear == "") {
					alert("请选择班级或学年！");
				} else {
					selectSummaryEva();
				}
			}

		}

		//对学生项目进行汇总
		function summaryEva() {
			var clazz = $("#stuSchClazz").val();
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			$("#evaluateScoreList1").html = "";
			$.getJSON("Json_evaluateSummaryByClazz", {
				clazz : clazz,
				schoolYear : schoolYear,
				startTime : startTime,
				endTime : endTime
			});
		}
		//查找学生项目评定结果，并显示在页面的表格中
		function selectSummaryEva() {
			var clazz = $("#stuSchClazz1").val();
			var startSchoolYear = $("#startSchoolYear1").val();
			var endSchoolYear = $("#endSchoolYear1").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			$("#evaluateScoreList tbody").html("");
			$
					.getJSON(
							"Json_selectSummaryEva",
							{
								clazz : clazz,
								schoolYear : schoolYear
							},
							function(data) {
								if (data.evaluateResults.length == "0") {
									alert("未找到相关数据！");
								} else {
									$
											.each(
													data.evaluateResults,
													function(i, value) {
														$("#evaluateScoreList")
																.append(

																		"<tr><td>"
																				+ value.student.stuSchNum
																				+ "</td><td >"
																				+ value.student.stuName
																				+ "</td><td class='M1'>"
																				+ parseFloat(
																						value.m1)
																						.toFixed(
																								2)
																				+ "</td><td class='M2'>"
																				+ parseFloat(
																						value.m2)
																						.toFixed(
																								2)
																				+ "</td><td class='M3'>"
																				+ parseFloat(
																						value.m3)
																						.toFixed(
																								2)
																				+ "</td><td class='M4'>"
																				+ parseFloat(
																						value.m4)
																						.toFixed(
																								2)
																				+ "</td><td class='M5'>"
																				+ parseFloat(
																						value.m5)
																						.toFixed(
																								2)
																				+ "</td><td><a href='TeacherStudent_Eva_Info_selectEvaluateResultById?evaluateResultId="
																				+ value.evaluateResultId
																				+ "&schoolYear="
																				+ value.schoolYear
																				+ "'>详情</a></td></tr>");
													});
								}

							});
		}
	</script>
</body>
</html>
