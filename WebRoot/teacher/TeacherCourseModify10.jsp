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

<title>修改课程信息10</title>

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
	window.onload = function() {
		for (var i = 0; i < input.length; i++) {
			if (input[i].value.toString().trim() == "0.0")
				input[i].value = "";
		}
		//文档加载完成后为表格中的每一个input添加name属性
		var tbody = document.getElementById("tbody");
		var inputs = tbody.getElementsByTagName("input");
		for (var i = 0; i < inputs.length / 3; i++) {
			inputs[3 * i + 0].setAttribute("name", "contributeTargets[" + i
					+ "].conTarValue");
			inputs[3 * i + 1].setAttribute("name", "contributeTargets[" + i
					+ "].teachingTarget.tchTargetId");
			inputs[3 * i + 2].setAttribute("name", "contributeTargets[" + i
					+ "].indicatorPoint.indPointId");
		}
	};

	/* 验证输入数据是否符合要求 */
	var input = document.getElementsByClassName("valueInput");
	function validate() {
		//input若为空则置零
		for (var i = 0; i < input.length; i++) {
			if (input[i].value.toString().trim().length == 0)
				input[i].value = 0;
		}

		var n = document.getElementsByClassName("countTr").length;//获取表格行数
		var count = input.length / n;//获取每一行的input数

		for (var i = 0; i < n; i++) {
			var total = 0;
			for (var j = 0; j < count; j++) {
				total += parseFloat(input[count * i + j].value) * 100;
			}
			if (total != 100) {
				alert("第" + (i + 1) + "行加和不为1，请检查。");
				return false;
			}
		}
		return true;
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10">
				<div class="div-module-add-curs">
					<h6>
						<img class="image-path-1" src="img/circle.jpg" /> <a
							href="Teacher_Management_3_selectTchrCourse">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">本课程对毕业要求指标点的支撑</div>
					<div class="div-curs-plan">
						<form action="TeacherCourse_Modify_10_modifyPointByCursId"
							method="post" onsubmit="javascript:return validate()">
							<div class="div-tbl-title">表-2：教学目标与毕业要求指标点支撑权重设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width48"></th>
										<s:iterator value="indicatorPoint" var="i">
											<th>指标点<s:property value="#i.indPointNum" /></th>
										</s:iterator>
									</tr>
								</thead>
								<tbody id="tbody">
									<s:iterator value="targets" var="t" status="s1">
										<tr class="countTr">
											<td>目标<s:property value="#s1.index+1" /></td>
											<s:iterator value="indicatorPoint" var="i" status="s2">
												<td id="innerTd"><s:if test="contributeTargets!=null&&contributeTargets.size()>0">
														<s:iterator
															value="contributeTargets.{?#this.teachingTarget.tchTargetId==#t.tchTargetId&&#this.indicatorPoint.indPointId==#i.indPointId}"
															var="c">
															<input class="border0 valueInput"
																value="<s:property value="#c.conTarValue" />" />
														</s:iterator>
													</s:if> <s:else>
														<input class="border0 valueInput" />
													</s:else> <input value="<s:property value="#t.tchTargetId"/>"
													class="hidden" /> <input
													value="<s:property value="#i.indPointId"/>" class="hidden" />
												</td>
											</s:iterator>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="div-btn">
								<input type="submit" class="btn" value="保存">
							</div>
						</form>
					</div>
				</div>
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
