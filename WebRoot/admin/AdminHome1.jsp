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

<title>教师信息列表</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	function clickInnerA(div){
		div.getElementsByTagName("a")[0].click();
	}
</script>
</head>

<body>
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h4>首页</h4>
					<hr/>
				</div>
				<div class="main">
					<div class="div-welcom">
					<img src="img/sun.png" /><label><strong><s:property value="#session.tUser.userName"/></strong>,您好！欢迎使用达成度评估系统。<a href="Admin_Modify_1_selectAllAdmin">账号设置</a></label>
					<hr/>
					</div>
					<div class="div-manage" onclick="clickInnerA(this)">
					<img src="img/manage_teacher.png" />
					<p><a href="AdminTeacher_List_1_selectAllTchrs">教师管理</a></p>
					<hr/>
					</div>
					<div class="div-manage" onclick="clickInnerA(this)">
					<img src="img/manage_student.png" />
					<p><a href="AdminStudent_List_1_selectAllStus">学生管理</a></p>
					<hr/>
					</div>
					<div class="div-manage" onclick="clickInnerA(this)">
					<img src="img/manage_course.png" />
					<p><a href="AdminCourse_List_1_selectAllCurs">课程管理</a></p>
					<hr/>
					</div>
					<div class="div-manage" onclick="clickInnerA(this)">
					<img src="img/manage_major.png" />
					<p><a href="Admin_MajorInformation_Modify_selectAllTchrs">专业信息</a></p>
					<hr/>
					</div>
					<div class="div-manage" onclick="clickInnerA(this)">
					<img src="img/manage_import.png" />
					<p><a href="admin/AdminImportExcel.jsp">信息批量导入</a></p>
					<hr/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	
	<script src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
