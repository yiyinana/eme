<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>课程详情6</title>

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
					<div class="div-inf-title">考核及成绩评定方式</div>
					<div class="div-inner-text">
						该课程各部分所占比例为：<s:if test="course.type=='normal'">
							期中成绩
						</s:if>
							<s:if test="course.type=='experiment'">
							查阅文献
						</s:if>
							<s:if test="course.type=='graduation-project'">
							论文
						</s:if>
						<s:property value="rules.midTermPer" />
						%， <s:if test="course.type=='normal'">
							期末成绩
						</s:if>
							<s:if test="course.type=='experiment'">
							查阅文献
						</s:if>
							<s:if test="course.type=='graduation-project'">
							答辩
						</s:if>
						<s:property value="rules.finalExamPer" />%，
						 <s:if test="course.type=='normal'">
							课堂表现
						</s:if>
							<s:if test="course.type=='experiment'">
							协作答辩
						</s:if>
							<s:if test="course.type=='graduation-project'">
							中期
						</s:if>：
						<s:property value="rules.clazzPer" />
						%， <s:if test="course.type=='normal'">
							平时作业
						</s:if>
							<s:if test="course.type=='experiment'">
							技术方案
						</s:if>
							<s:if test="course.type=='graduation-project'">
							软硬件验收
						</s:if>：
						<s:property value="rules.homeworkResultPer" />
						%， <s:if test="course.type=='normal'">
							实验成绩<s:property value="rules.expResultPer" />
						%。
						</s:if>
							<s:if test="course.type=='experiment'">
						</s:if>
							<s:if test="course.type=='graduation-project'">
							翻译<s:property value="rules.expResultPer" />
						%。
						</s:if>
						
					</div>
				</div>
				<div class="div-curs-plan">
					<div class="div-tbl-title">表-2：教学目标与毕业要求指标点支撑权重设置表</div>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th class="width48"></th>
								<s:iterator value="indicatorPoints" var="i">
									<th>指标点<s:property value="#i.indPointNum" /></th>
								</s:iterator>
							</tr>
						</thead>
						<tbody id="tbody">
							<s:iterator value="targets" var="t" status="s1">
								<tr class="countTr">
									<td>目标<s:property value="#s1.index+1" /></td>
									<s:iterator value="indicatorPoints" var="i" status="s2">
										<td><s:iterator
												value="contributeTargets.{?#this.teachingTarget.tchTargetId==#t.tchTargetId&&#this.indicatorPoint.indPointId==#i.indPointId}"
												var="c">
												<s:property value="#c.conTarValue" />
											</s:iterator></td>
									</s:iterator>
								</tr>
							</s:iterator>
						</tbody>
					</table>
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
