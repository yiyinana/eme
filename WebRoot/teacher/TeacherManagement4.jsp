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

<title>教学管理4</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/bootstrap-multiselect.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function check(form) {
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			alert("请选择文件！");
			return false;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "xls" && postf != "xlsx") {
			alert("您选择的文件格式不正确！");
			return false;
		}
		document.getElementById("fileName").value = filename;
		return true;
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<!-- 成绩评定与查询 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>成绩评定与查询</label>
								</div>
								<div class="div-inf-tbl">
									<div class="div-result-import">
										<s:form action="FileUpload" method="post"
											enctype="multipart/form-data">
											<h5>
												<s:property value="currentTerm" />
											</h5>
											<div class="div-select">
												课程&nbsp;&nbsp;<select id="course" name="course">
													<s:iterator value="tchrCourses" var="c">
														<option value="<s:property value="#c.cursName"/>"><s:property
																value="#c.cursName" /></option>
													</s:iterator>
												</select>
											</div>
											<div class="div-select">
												班级&nbsp;&nbsp;
												<select id="multiselect" name="clazz" multiple="multiple">
													<s:iterator value="allClazz" var="c">
														<option value="<s:property value="#c.claName"/>"><s:property
																value="#c.claName" /></option>
													</s:iterator>
												</select>
											</div>
											<a class="btn btn-file">选择文件<input type="file"
												name="file" id="filename"></a>
											<input name="uploadUrl" value="/excel" class="hidden" />
											<input id="fileName" type="text" class="hidden"
												name="fileName" />
											<br />
											<input type="submit" value="上传成绩" class="btn"
												onclick="return check(this.form)">
										</s:form>
									</div>
								</div>

								<div class="div-inf-tbl">
									<div class="div-result-import">
										<form action="Teacher_Management_2_selectStuPer" method="post"
											enctype="multipart/form-data">
											<h5>
												<s:property value="currentTerm" />
											</h5>
											<div class="div-select">
												课程&nbsp;&nbsp;<select id="course"
													name="stuCursLimit.cursName">
													<s:iterator value="tchrCourses" var="c">
														<option value="<s:property value="#c.cursName"/>"><s:property
																value="#c.cursName" /></option>
													</s:iterator>
												</select>
											</div>
											<div class="div-select">
												班级&nbsp;&nbsp;<select id="class" name="stuCursLimit.claName">
													<option value="" selected="selected">全部</option>
													<s:iterator value="allClazz" var="c">
														<option value="<s:property value="#c.claName"/>"><s:property
																value="#c.claName" /></option>
													</s:iterator>
												</select>
											</div>
											<input class="btn" type="submit" value="查询成绩">
										</form>
									</div>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th class="th1">学号</th>
												<th class="th2">姓名</th>
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
												<th class="th8">综合成绩</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="stuPerformances" var="p">
												<tr>
													<td><s:property value="#p.student.stuSchNum" /></td>
													<td><s:property value="#p.student.stuName" /></td>
													<td><s:property value="#p.ClassEvaValue" /></td>
													<td><s:property value="#p.WorkEvaValue" /></td>
													<s:if test="course.type=='experiment'">
													<td style="display:none"><s:property value="#p.ExpEvaValue" /></td>
													</s:if>
													<s:else>
													<td><s:property value="#p.ExpEvaValue" /></td>
													</s:else>
													<td><s:property value="#p.MidEvaValue" /></td>
													<td><s:property value="#p.FinEvaValue" /></td>
													<td><s:property value="#p.EvaValue" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>

							</div>
							<!-- 成绩评定与查询完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<script>
		$(function() {
			$('#multiselect').multiselect();
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
