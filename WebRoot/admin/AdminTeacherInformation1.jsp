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

<title>教师信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/admin.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
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
	<%@ include file="/include/admin-header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span10 offset1">
				<div class="div-module">
					<h6><img class="image-path-1" src="img/circle.jpg"/>
						<a href="admin/AdminHome1.jsp">首页</a><img class="image-path-2" src="img/zhexian.jpg"/><a
							href="AdminTeacher_List_1_selectAllTchrs">教师信息管理</a><img class="image-path-2" src="img/zhexian.jpg"/>教师详细信息
					</h6>
				</div>
				<!-- 教师基本信息 -->
				<div class="div-tchr-basic-inf">
					<div class="div-tchr-basic-inf-left">
						<h5>基本信息</h5>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrSchNum" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrName" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:if test="%{teacher.tchrGender==true}">男</s:if>
								<s:else>女</s:else>
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">出生年月</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrBirthday" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">最高学位</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrDegree" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrTitle" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">所学专业</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrMajor" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">毕业院校</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrGraduateSchool" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">任职时间</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrAttendDate" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrPhone" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrFax" />
							</div>
						</div>

						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">电子邮件</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrEmail" />
							</div>
						</div>
						<div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">办&nbsp;&nbsp;公&nbsp;&nbsp;室</div>
							<div class="div-tchr-basic-inf-1-2">
								<s:property value="teacher.tchrOfficeAddr" />
							</div>
						</div>
						<!-- <div class="div-tchr-basic-inf-1">
							<div class="div-tchr-basic-inf-1-1">个人主页</div>
							<div class="div-tchr-basic-inf-1-2">
								<a id="tchrPage" href="#">www.baidu.com</a>
							</div>
						</div> -->
					</div>
					<div class="div-tchr-basic-inf-right">
						<img src="tchrImg/<s:property value="teacher.tchrSchNum"/>.jpg"
							class="img-polaroid img-head">
					</div>
				</div>
				<div class="div-tchr-self-intr">
					<h5>个人简介：</h5>
					<div class="div-tchr-self-intr-content">
						<label><s:property value="teacher.tchrSelflIntroduction" /></label>
					</div>
				</div>
				<!-- 教师基本信息完 -->
				<!-- 教师学历履历 -->
				<div class="div-inf-tbl">
					<h5>学历&nbsp;履历</h5>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>学校/单位</th>
								<th>学位/职位</th>
								<th>时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="experiments" var="e">
										<tr>
											<td><s:property value="#e.school" /></td>
											<td><s:property value="#e.position" /></td>
											<td><s:property value="#e.time" /></td>
										</tr>
									</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 教师学历履历完 -->
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
