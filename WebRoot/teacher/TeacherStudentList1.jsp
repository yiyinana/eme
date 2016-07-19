<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>学生列表</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<style type="text/css">
.stu-table {
	width: 600px;
}
</style>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="span9 div-content-white-bgr">
						<div class="div-inf-bar">
							<label>学生信息列表</label>
						</div>
						<div class="div-inf-tbl">
							<form action="TeacherStudent_List_1_selectStusByLimits"
								class="form-search" method="post">
								<div class="div-select" style="width: 85%">
									<label>学号</label> <input type="text" name="limits.stuSchNum"
										style="width: 120px" id="stuSchNum">
									&nbsp;&nbsp;&nbsp;&nbsp;<label>姓名</label> <input type="text"
										name="limits.stuName" style="width: 120px" id="stuName">&nbsp;&nbsp;&nbsp;&nbsp;

									<label>班级</label> <select name="limits.stuClazz" id="stuClazz"
										style="width: 140px">
										<option value="" selected="selected">全部</option>
										<s:iterator value="allClazz" var="c">
											<option value="<s:property value="#c.claId"/>"><s:property
													value="#c.claName" /></option>
										</s:iterator>
									</select>

								</div>
								<input type="submit" class="btn" value="查找">
								<!-- <button class="btn" >查找</button> -->
							</form>
							<%-- <div style="position: relative; ">
								<label><s:actionerror /></label><a class="btn top0"
									href="TeacherStudent_Add_1_selectAllClazz">新增学生信息</a>
							</div> --%>
							<div class="div-msg">
								<label style="color: red"><s:actionerror /></label>
							</div>
							<div class="div-tchr-detail">
								<table class="table table-bordered table-condensed"
									id="adminStuList">
									<thead>
										<tr>
											<th width="40px">学号</th>
											<th width="40px">姓名</th>
											<th width="80px">班级</th>
											<th width="80px">电话</th>
											<th width="120px">操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="students" var="s">
											<tr>
												<td><s:property value="#s.stuSchNum" /></td>
												<td><s:property value="#s.stuName" /></td>
												<td><s:property value="#s.clazz.claName" /></td>
												<td><s:property value="#s.stuPhone" /></td>
												<td><a
													href="TeacherStudent_Information_1_selectStudentBySchNum?schNum=<s:property value="#s.stuSchNum"/>">详细</a>&nbsp;&nbsp;
													<a
													href="TeacherStudent_Information_Modify_selectStudentBySchNum?schNum=<s:property value="#s.stuSchNum"/>">编辑</a>&nbsp;&nbsp;
													<a onclick="return confirm('确认删除？')"
													href="TeacherStudent_List_1_deleteBySchNum?schNum=<s:property value="#s.stuSchNum"/>">删除</a>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script src="js/stuListPage.js"></script>
	<script type="text/javascript">
		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
		
	</script>
</body>
</html>
