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

<title>添加课程信息5</title>

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
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminCourse_List_1_selectAllCurs">课程信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>添加课程贡献点
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">本课程对毕业要求指标点的支撑</div>
					<div class="div-curs-plan">
					<form action="AdminCourse_Add_10_selectTargetAndPointByCursId" method="post">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th class="width24">毕业要求</th>
									<th>指标点</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="indicatorPoints" status="s">
									<tr>
										<td>毕业要求<s:property value="#s.index+1" /></td>
										<td class="indPointList"><s:iterator value="top" id="inner">
												<label title="<s:property value="#inner.indPointContent" />"><input type="checkbox"
												 name="indicatorPoint.indPointId"	value="<s:property value="#inner.indPointId"/>">
												<s:property value="#inner.indPointNum" /></label>
											</s:iterator></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="div-btn">
							<input type="submit" value="下一步" class="btn">
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
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
