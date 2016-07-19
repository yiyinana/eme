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

.text-size {
	font-family: 微软雅黑, 宋体;
	font-size: 16px;
}

.left-distance {
	margin-left: 20px
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
								<span class="text-size ">班级：</span>&nbsp;&nbsp;<select
									name="limits.stuClazz" id="stuSchClazz"
									style="width: 140px; height: 30px">
									<option value="" selected="selected">全部</option>
									<s:iterator value="allClazz" var="c">
										<option value="<s:property value="#c.claId"/>"><s:property
												value="#c.claName" /></option>
									</s:iterator>
								</select><span class="text-size left-distance">评定学年：</span><input
									 type="text" name="startSchoolYear"
									id="startSchoolYear" style="width: 40px"
									onchange="changeEndYear()"
									onFocus="WdatePicker(WdatePicker({lang:'zh-cn',dateFmt:'yyyy',readOnly:'true'})) ">
								<span class="text-size ">至</span>&nbsp;&nbsp;<input
									type="text" name="endSchoolYear" id="endSchoolYear"
									style="width: 40px" readOnly>
										<input type="button" id="evaSummary" class="btn left-distance" value="评估汇总"
								onclick="isEmpity()"> 
							</div>
							<input type="button" value="查看评估结果" class="btn" name="checkEva" id="checkEva" onclick="selectEva()">
						<input type="button"
								value="导出Excel" class="btn">
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
		function changeEndYear() {
			var startSchoolYear = $("#startSchoolYear").val();
			document.getElementById("endSchoolYear").value = parseInt(startSchoolYear) + 1;
		}
		//判断评估输入是否为空
		function isEmpity() {
			var clazz = $("#stuSchClazz").val();
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			if (clazz == "" || startSchoolYear == "" || endSchoolYear == "") {
				alert("请选择班级或学年！");
			} else {
				summaryEva();
			}
		}
		
		function isEmpty1(){
			var clazz = $("#stuSchClazz").val();
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			if (clazz == "" || startSchoolYear == "" || endSchoolYear == "") {
				alert("请选择班级或学年！");
			} else {
				selectEva();
			}
		}
		//对学生项目进行汇总
		function summaryEva() {
			var clazz = $("#stuSchClazz").val();
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			$("#evaluateScoreList1").html = "";
			$.getJSON("Json_evaluateSummaryByClazz", {
				clazz : clazz,
				schoolYear : schoolYear
			});
		}
	
		function selectEva() {
			var clazz = $("#stuSchClazz").val();
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			selectSummaryEva(clazz, schoolYear);
		}
		//查找学生项目评定结果，并显示在页面的表格中
		function selectSummaryEva(clazz, schoolYear) {
			$("#evaluateScoreList tbody").html("");
			$.getJSON("Json_selectSummaryEva", {
				clazz : clazz,
				schoolYear : schoolYear
			}, function(data) {
				$.each(data.evaluateResults, function(i, value) {
					$("#evaluateScoreList").append(
			
							"<tr><td>" + value.student.stuSchNum + "</td><td >"
									+ value.student.stuName + "</td><td class='M1'>" + parseFloat(value.m1).toFixed(2)
									+ "</td><td class='M2'>" + parseFloat(value.m2).toFixed(2) + "</td><td class='M3'>"
									+ parseFloat(value.m3).toFixed(2) + "</td><td class='M4'>" +parseFloat(value.m4).toFixed(2)
									+ "</td><td class='M5'>" + parseFloat(value.m5).toFixed(2) + "</td><td><a href='TeacherStudent_Eva_Info_selectEvaluateResultById?evaluateResultId="+value.evaluateResultId+"&schoolYear="+value.schoolYear+"'>详情</a></td></tr>");
				});
			});
		}
		
		
	</script>
</body>
</html>
