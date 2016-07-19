<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>教学管理2</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
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
							<!-- 研究成果 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>课程大纲管理</label>
								</div>
								<div class="div-tchr-course">
									<s:iterator value="chargeCourses" var="c">
										<div class="div-curs">
											<h6>课程名称：</h6>
											<label><s:property value="#c.cursName" /></label><br />
											<h6>课程编号：</h6>
											<label><s:property value="#c.cursNum" /></label><br />
											<h6>学分/学时：</h6>
											<label><s:property value="#c.cursCredit" />/<s:property
													value="#c.cursClassHour" /></label><br />
											<h6>课程性质：</h6>
											<label><s:property value="#c.cursProperty" /></label><br />
											<h6>开设学期：</h6>
											<label><s:property value="#c.cursTerm" /></label>
											<div class="div-curs-detail-inf a">
												<a
													href="TeacherCourse_Modify_1_selectByCursId?cursId=<s:property value="#c.cursId"/>">修改</a>&nbsp;&nbsp;
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
							<!-- 研究成果完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
