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

<title>修改课程信息5</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<script src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/include/course-modify-left.jsp"%>
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
						<form action="TeacherCourse_Modify_10_selectTargetAndPointById"
							method="post">
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
											<td><label
												title="<s:property value="requirement[#s.index].graReqContent" />"><s:property
														value="requirement[#s.index].graReqTitle" /></label></td>
											<td class="indPointList"><s:iterator value="top"
													id="inner">
													<label
														title="<s:property value="#inner.indPointContent" />"><input
														type="checkbox" name="indicatorPoint.indPointId"
														value="<s:property value="#inner.indPointId"/>"> <s:property
															value="#inner.indPointNum" /></label>
												</s:iterator></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="div-btn">
								<input type="submit" value="设置毕业要求评价值" class="btn">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>

	<script src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
