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

<title>My JSP 'teacher_information_1.jsp' starting page</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_goal.css" />
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/student_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/stuLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<!-- 学生目标 -->
								<div class="div-inf-bar">
									<label>规划与目标</label>
								</div>
								<div class="div-inf-tbl">
									<h5>短期目标</h5>
									<div class="div-goal-content">
										<s:property value="s.shortGoal" />
									</div>
									<label class="lable-add" data-toggle="modal" data-target="#myModal1">修改</label>
								</div>
								
								<div class="div-inf-tbl">
									<h5>中期目标</h5>
									<div class="div-goal-content">
										<s:property value="s.midGoal" />
									</div>
									<label class="lable-add" data-toggle="modal" data-target="#myModal2">修改</label>
								</div>
			
								<div class="div-inf-tbl">
									<h5>长期目标</h5>
									<div class="div-goal-content">
										<s:property value="s.longGoal" />
									</div>
									<label class="lable-add" data-toggle="modal" data-target="#myModal3">修改</label>
								</div>
							<!-- 学生目标完 -->
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<!-- 模态框，用于修改短期目标 -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改短期目标</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Portfolio_Goal_updateShortGoal" method="post"
						class="form-add">
						<textarea name="shortGoal" class="textarea-modal"><s:property value="s.shortGoal" /></textarea>
						<div class="div-btn">
									<input type="submit" value="提交" class="btn">
								</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于修改短期目标 -->
	<!-- 模态框，用于修改中期目标 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改中期目标</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Portfolio_Goal_updateMidGoal" method="post"
						class="form-add">
						<textarea name="midGoal" class="textarea-modal"><s:property value="s.midGoal" /></textarea>
						<div class="div-btn">
									<input type="submit" value="提交" class="btn">
								</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于修改中期目标 -->
	<!-- 模态框，用于修改长期目标 -->
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改长期目标</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Portfolio_Goal_updateLongGoal" method="post"
						class="form-add">
						<textarea name="longGoal" class="textarea-modal"><s:property value="s.longGoal" /></textarea>
						<div class="div-btn">
									<input type="submit" value="提交" class="btn">
								</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于修改长期目标 -->
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88-41+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
