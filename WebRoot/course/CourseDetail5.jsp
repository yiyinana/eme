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

<title>课程详情5</title>

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
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="Course_1_1_goBack">课程列表</a><img class="image-path-2" src="img/zhexian.jpg"/><s:property value="course.cursName" />
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">本课程对培养学生能力和素质的贡献点</div>
					<div class="div-inner-text">在课堂教学中，通过讲授、提问、讨论、演示等教学方法和手段，提高学生逻辑思维能力和表述能力，锻炼学生对知识和观点的表达，同时也能提高学习兴趣。
						教学中设置相应的案例分析和教学录像，设计合理的课堂任务，组织学生进行分组讨论，借此激发学生的潜能，同时向学生讲述分析问题的方法，提高学生对知识
						的应用能力。结合企业发展动态，对制造技术新的应用方向和应用内容进行扩充讲解，并可以发动学生进行有关调研，以提高学生学习兴趣。课后鼓励学生充分利
						用网络信息资源，搜集相关案例和学习资源，扩充课堂教学内容涉及的知识和应用。</div>
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
