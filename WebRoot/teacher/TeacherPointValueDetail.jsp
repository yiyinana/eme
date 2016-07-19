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

<title>教学管理</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	$(function() {
		//补全第一张表
		var tds = document.getElementsByClassName("value");
		var totalTd = document.getElementsByClassName("total");
		var totalTr = document.getElementById("total");
		var n = totalTd.length;//获取行数
		//第二列
		for (var i = 0; i < n; i++) {
			totalTd[i].innerHTML = parseFloat(tds[i * 12 + 0].innerHTML)
					+ parseFloat(tds[i * 12 + 2].innerHTML)
					+ parseFloat(tds[i * 12 + 4].innerHTML)
					+ parseFloat(tds[i * 12 + 6].innerHTML)
					+ parseFloat(tds[i * 12 + 8].innerHTML);
		}

		//最后一行
		var tds2 = totalTr.getElementsByTagName("td");
		var total0 = 0, total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0, total6 = 0, total7 = 0, total8 = 0, total9 = 0, total10 = 0;
		for (var i = 0; i < n; i++) {
			total0 += parseFloat(tds[i * 12 + 0].innerHTML);
			total1 += parseFloat(tds[i * 12 + 1].innerHTML);
			total2 += parseFloat(tds[i * 12 + 2].innerHTML);
			total3 += parseFloat(tds[i * 12 + 3].innerHTML);
			total4 += parseFloat(tds[i * 12 + 4].innerHTML);
			total5 += parseFloat(tds[i * 12 + 5].innerHTML);
			total6 += parseFloat(tds[i * 12 + 6].innerHTML);
			total7 += parseFloat(tds[i * 12 + 7].innerHTML);
			total8 += parseFloat(tds[i * 12 + 8].innerHTML);
			total9 += parseFloat(tds[i * 12 + 9].innerHTML);
			total10 += parseFloat(tds[i * 12 + 10].innerHTML);
		}
		tds2[2].innerHTML = total0.toFixed(2);
		tds2[3].innerHTML = total1.toFixed(2);
		tds2[4].innerHTML = total2.toFixed(2);
		tds2[5].innerHTML = total3.toFixed(2);
		tds2[6].innerHTML = total4.toFixed(2);
		tds2[7].innerHTML = total5.toFixed(2);
		tds2[8].innerHTML = total6.toFixed(2);
		tds2[9].innerHTML = total7.toFixed(2);
		tds2[10].innerHTML = total8.toFixed(2);
		tds2[11].innerHTML = total9.toFixed(2);
		tds2[12].innerHTML = total10.toFixed(2);
		tds2[13].innerHTML = (total10 / 100).toFixed(3);
	});
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span12 div-content-white-bgr">
					<div class="div-inf-tbl">
						<div class="div-tbl-title">《<s:property value="cursName" />》课程教学目标达成度评价表</div>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th rowspan="2">教学目标</th>
									<th rowspan="2">目标值</th>
									<s:if test="course.type=='normal'">
								 			<th colspan="2">课堂表现</th>
											<th colspan="2">平时作业</th>
											<th colspan="2">实验成绩</th>
											<th colspan="2">期中成绩</th>
											<th colspan="2">期末成绩</th>
										</s:if>
										<s:if test="course.type=='experiment'">
							 				<th colspan="2">团队协作</th>
											<th colspan="2">技术方案</th>
											<th colspan="2">设计报告</th>
											<th colspan="2">答辩</th>
											<th colspan="2">查阅文献</th>
										</s:if>
										<s:if test="course.type=='graduation-project'">
							 				<th colspan="2">中期</th>
											<th colspan="2">软硬件验收</th>
											<th colspan="2">翻译</th>
											<th colspan="2">论文</th>
											<th colspan="2">答辩</th>
										</s:if>
									<th colspan="2">总评价值</th>
								</tr>
								<tr>
									<th>目标值1</th>
									<th>评价值1</th>
									<th>目标值2</th>
									<th>评价值2</th>
									<th>目标值3</th>
									<th>评价值3</th>
									<th>目标值4</th>
									<th>评价值4</th>
									<th>目标值5</th>
									<th>评价值5</th>
									<th>α<small>1</small></th>
									<th>β<small>1</small></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="claCursB1s" var="b1">
									<tr>
										<td><s:property value="#b1.target" /></td>
										<td class="total"></td>
										<td class="value"><s:property
												value="#b1.tchtargetClassTargetValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetClassEvaValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetHomeworkTargetValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetWorkEvaValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetExpTargetValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetExpEvaValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetMidTargetValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetMidEvaValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetFinTargetValue" /></td>
										<td class="value"><s:property
												value="#b1.tchtargetFinEvaValue" /></td>
										<td class="value"><s:property value="#b1.a1" /></td>
										<td class="value"><s:property value="#b1.b1" /></td>
									</tr>
								</s:iterator>
								<tr id="total">
									<td>合计</td>
									<td>100</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<label>α1：教学目标评价值之和；β1：等于α1/目标值</label>
					</div>
					<div class="div-inf-tbl">
						<div class="div-tbl-title">《<s:property value="cursName" />》课程对毕业要求达成度评价表</div>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th rowspan="2"></th>
									<th rowspan="2">达成度目标值</th>
									<th colspan="2">评价值</th>
								</tr>
								<tr>
									<th>α<small>2</small></th>
									<th>β<small>2</small></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="claCursB2s" var="b2">
									<tr>
										<td class="td-left"><s:property value="#b2.point" /></td>
										<td><s:property value="#b2.targetTarValue" /></td>
										<td><s:property value="#b2.a2" /></td>
										<td><s:property value="#b2.b2" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<label>α2：毕业要求评价值=∑达成度目标值*β1<br>β2：=α2/达成度目标值</label>
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
