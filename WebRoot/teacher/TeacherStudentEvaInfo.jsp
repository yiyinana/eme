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
							<label>学生评估雷达图</label>
						</div>
						<div class="div-inf-tbl">
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
											<th class="hidden" width="40px">MaxM1</th>
											<th class="hidden" width="40px">MaxM2</th>
											<th class="hidden" width="40px">MaxM3</th>
											<th class="hidden" width="40px">MaxM4</th>
											<th class="hidden" width="40px">MaxM5</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><s:property value="evaluateResult.student.stuSchNum" /></td>
											<td><s:property value="evaluateResult.student.stuName" /></td>
											<td class="M1"><s:property value="evaluateResult.M1" /></td>
											<td class="M2"><s:property value="evaluateResult.M2" /></td>
											<td class="M3"><s:property value="evaluateResult.M3" /></td>
											<td class="M4"><s:property value="evaluateResult.M4" /></td>
											<td class="M5"><s:property value="evaluateResult.M5" /></td>
											<td class="hidden" id="maxM1"><s:property
													value="maxEva.maxM1" /></td>
											<td class="hidden" id="maxM2"><s:property
													value="maxEva.maxM2" /></td>
											<td class="hidden" id="maxM3"><s:property
													value="maxEva.maxM3" /></td>
											<td class="hidden" id="maxM4"><s:property
													value="maxEva.maxM4" /></td>
											<td class="hidden" id="maxM5"><s:property
													value="maxEva.maxM5" /></td>
										</tr>
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
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/radarChart.js"></script>
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

			//格式化表格中的数据
			for (var i = 1; i < 6; i++) {
				document.getElementsByClassName("M" + i)[0].innerHTML = parseFloat(
						document.getElementsByClassName("M" + i)[0].innerHTML)
						.toFixed(2);
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
			var maxM1 = $("#maxM1").html();
			var maxM2 = $("#maxM2").html();
			var maxM3 = $("#maxM3").html();
			var maxM4 = $("#maxM4").html();
			var maxM5 = $("#maxM5").html();
			console.log(maxM1 + "  " + maxM2 + "  " + maxM3 + "  " + maxM4
					+ "  " + maxM5);

			//转换成百分制
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
		});
	</script>
</body>
</html>
