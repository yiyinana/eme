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
								<label>项目信息</label>
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
										class="btn left-distance" value="查询结果"
										onclick="selectEvaluateResult()">
								</div>
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
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<div class="div-radar">
								<canvas id="canvas" width="450px" height="450px"></canvas>
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
	<script type="text/javascript" src="js/radarChart.js"></script>

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
		function selectEvaluateResult() {
			$("#canvas").html("");
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			$("#evaluateScoreList tbody").html("");
			$.getJSON("Json_selectEvaluateResult", {
				schoolYear : schoolYear
			}, function(data) {
				$("#evaluateScoreList").append(
						"<tr><td>" + data.evaluateResult.student.stuSchNum
								+ "</td><td>"
								+ data.evaluateResult.student.stuName
								+ "</td><td class='M1'>"
								+ data.evaluateResult.m1
								+ "</td><td class='M2'>"
								+ data.evaluateResult.m2
								+ "</td><td class='M3'>"
								+ data.evaluateResult.m3
								+ "</td><td class='M4'>"
								+ data.evaluateResult.m4
								+ "</td><td class='M5'>"
								+ data.evaluateResult.m5 + "</td></tr>");
			
				radarPhoto(data.maxEva.maxM1,data.maxEva.maxM2,data.maxEva.maxM3,data.maxEva.maxM4,data.maxEva.maxM5);
			});
		}
		
		function radarPhoto(maxM1,maxM2,maxM3,maxM4,maxM5) {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
					
			//格式化表格中的数据
			for(var i=1;i<6;i++){
				document.getElementsByClassName("M"+i)[0].innerHTML = parseFloat(document.getElementsByClassName("M"+i)[0].innerHTML).toFixed(2);
			}
			
			/**
			 * 开始绘制雷达图
			 */
			var canvas = document.getElementById("canvas");
			var context = canvas.getContext('2d');
			context.beginPath();
			//先绘制五个五边形
			polygon(context, 5, 240, 240, 40);
			polygon(context, 5, 240, 240, 80);
			polygon(context, 5, 240, 240, 120);
			polygon(context, 5, 240, 240, 160);
			polygon(context, 5, 240, 240, 200);
			//绘制连接的五条线
			line(context, 5, 240, 240, 200);
			context.strokeStyle = "#B3B3B3";
			context.lineWidth = 1;
			context.stroke();
			context.closePath();
			//绘制标尺
			context.beginPath();
			context.font = "14px sans-serif";
			context.strokeStyle = "#4D4D4D";
			context.strokeText("20", 246, (240 - 30));
			context.strokeText("40", 246, (240 - 70));
			context.strokeText("60", 246, (240 - 110));
			context.strokeText("80", 246, (240 - 150));
			context.strokeText("100", 246, (240 - 190));
			//分项关键字
			var keyWords = [ "M1", "M2", "M3", "M4", "M5" ];
			keyWord(context, 5, 240, 240, 200, 0, false, keyWords);
			context.closePath();
			//基本框架至此绘制完毕

			//根据数据绘制折线
			context.beginPath();
			var M1 = $(".M1").html();
			var M2 = $(".M2").html();
			var M3 = $(".M3").html();
			var M4 = $(".M4").html();
			var M5 = $(".M5").html();
			if (maxM1 == 0)
				maxM1 = 1;
			if (maxM2 == 0)
				maxM2 = 1;
			if (maxM3 == 0)
				maxM3 = 1;
			if (maxM4 == 0)
				maxM4 = 1;
			if (maxM5 == 0)
				maxM5 = 1;
			value = [ M1 / maxM1 * 100, M2 / maxM2 * 100, M3 / maxM3 * 100,
						M4 / maxM4 * 100, M5 / maxM5 * 100 ];
			radarChart(context, 5, 240, 240, 200, 0, false, value);
			context.strokeStyle = "#00B2EE";
			context.lineWidth = 3;
			context.stroke();
			context.closePath();
			/**
			 * 雷达图绘制完毕
			 */
		}
	</script>
</body>
</html>
