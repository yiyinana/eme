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

<title>学生参与项目页面</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_goal.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>

<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。

	function isEmpty() {
		var time = document.getElementById("input-time");
		var name = document.getElementById("input-name");
		var unit = document.getElementById("input-unit");
		var duty = document.getElementById("input-duty");

		if (time.value.toString().trim().length != 0
				&& name.value.toString().trim().length != 0
				&& unit.value.toString().trim().length != 0
				&& duty.value.toString().trim().length != 0) {
			return true;
		} else {
			if (time.value.toString().trim().length == 0) {
				time.focus();
			}
			if (name.value.toString().trim().length == 0) {
				name.focus();
			}
			if (unit.value.toString().trim().length == 0) {
				unit.focus();
			}
			if (duty.value.toString().trim().length == 0) {
				duty.focus();
			}
			alert("输入框不能为空！");
			return false;
		}
	}
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
						<div class="span9 div-content-white-bgr" style=" min-height:440px ">
							<div class="div-inf-bar">
								<label>学生参与项目</label>
							</div>
							<div class="div-inf-tbl">
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>项目编号</th>
											<th>项目名称</th>
											<th>审核状态</th>
											<th>得分</th>
											<th>操作</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="items" var="i">
											<tr>
												<td><s:property value="#i.itemNum" /></td>
												<td><s:property value="#i.itemName" /></td>
												<td><s:property value="#i.itemState" /></td>
												<td><s:property value="#i.itemScore" /></td>
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteItem?itemId=<s:property value="#i.stuItemId"/>">删除</a></td>
												<td><a href="Student_Award_Info_selectItemInfo?itemId=<s:property value="#i.stuItemId"/>">详情</a></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<label class="lable-add"><a href="Student_Award_Add_selectItemEvaType">添加</a></label>
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
