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

<title>课程详情2</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/course-detail.css" />

</head>

<body>
	<%@ include file="/include/course-detail-header.jsp"%>
	<div class="container">
		<div class="row">
			<%@ include file="/include/course-detail-left.jsp"%>
			<div class="span10">
				<div class="div-module-add-curs">
					<h6>
						<img class="image-path-1" src="img/circle.jpg" /> <a
							href="Course_1_1_goBack">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">目标与任务</div>

					<div class="div-inner-text">
						<h6>通过本课程的教学，使学生具备以下知识和能力：</h6>
						<s:iterator value="targets" var="t" status="s">
							<p>
								<s:property value="#s.index+1" />
								.
								<s:property value="#t.tchTarContent" />
							</p>

						</s:iterator>
					</div>
				</div>

				<div class="div-inf">
					<div class="div-inf-title">课程教学目标与毕业要求对应关系</div>
					<div class="div-curs-plan">
							<div class="div-tbl-title">表-1：课程考试与教学目标支撑分值设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width48">项目</th>
										<s:if test="course.type=='normal'">
								 			<th>课堂表现</th>
											<th>平时作业</th>
											<th>实验成绩</th>
											<th>期中成绩</th>
											<th>期末成绩</th>
										</s:if>
										<s:if test="course.type=='experiment'">
							 				<th>协作答辩</th>
											<th>技术方案</th>
											<th style="display:none"></th>
											<th>设计报告</th>
											<th>查阅文献</th>
										</s:if>
										<s:if test="course.type=='graduation-project'">
							 				<th>中期</th>
											<th>软硬件验收</th>
											<th>翻译</th>
											<th>论文</th>
											<th>答辩</th>
										</s:if>
									</tr>
								</thead>
								<tbody>
								<s:iterator value="targets" var="t" status="s">
								<s:if test="course.type=='experiment'">
									<tr>
										<td>目标<s:property value="#s.index+1"/></td>
										<td><s:property value="#t.tchtargetClassTargetValue"/></td>
					 					<td><s:property value="#t.tchtargetHomeworkTargetValue"/></td>
										<td style="display:none"><s:property value="#t.tchtargetExpTargetValue"/></td>
										<td><s:property value="#t.tchtargetMidTargetValue"/></td>
										<td><s:property value="#t.tchtargetFinTargetValue"/></td>
									</tr>
									</s:if>
									<s:else>
									<tr>
										<td>目标<s:property value="#s.index+1"/></td>
										<td><s:property value="#t.tchtargetClassTargetValue"/></td>
					 					<td><s:property value="#t.tchtargetHomeworkTargetValue"/></td>
										<td><s:property value="#t.tchtargetExpTargetValue"/></td>
										<td><s:property value="#t.tchtargetMidTargetValue"/></td>
										<td><s:property value="#t.tchtargetFinTargetValue"/></td>
									</tr>
									</s:else>
								</s:iterator>
								</tbody>
							</table>
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
