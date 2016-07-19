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

<title>学生信息修改</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_information.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	function isEmpty(){
		var pwd1 = document.getElementById("input-password1").value.toString();
		var pwd2 = document.getElementById("input-password2").value.toString();
		if(pwd1.trim().length == 0){
			alert("密码不能为空");
			return false;
		}
		else{
			if(pwd1!=pwd2){
				alert("两次密码不相同");
				return false;
			}
			return true;
		}
	}
</script>
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
							<!-- 学生基本信息 -->
							<div class="div-inf-bar">
								<label>修改基本信息</label>
							</div>
								<form class="form-horizontal" action="Student_Information_Modify_modifyBasicInf"
									method="post">
									<div class="control-group">
										<label class="control-label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;</label>
										<div class="controls"><input type="text" name="s.stuSchNum"
											id="stuSchNum" value="<s:property value="s.stuSchNum"/>"
											disabled="disabled"><label class="lable-modify" data-toggle="modal"
									data-target="#myModal">修改密码</label>
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="s.stuName"
											id="stuName" value="<s:property value="s.stuName"/>"
											>
											</div>
									</div>
									<%-- 
									<div class="control-group">
										<lable class="control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;</lable> <input type="text" name="s.stuGender"
											id="stuGender" value="<s:property value="s.stuGender"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族&nbsp;:&nbsp;</lable> <input type="text" name="s.stuNation"
											id="stuNation" value="<s:property value="s.stuNation"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;:&nbsp;</lable> <input type="text" name="s.dept.deptName"
											id="deptName" value="<s:property value="s.dept.deptName"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;:&nbsp;</lable> <input type="text" name="s.clazz.clazzName"
											id="clazzName"
											value="<s:property value="s.clazz.clazzName"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">入学日期&nbsp;:&nbsp;</lable> <input type="text" name="s.stuAttendDate"
											id="stuAttendDate"
											value="<s:property value="s.stuAttendDate"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制&nbsp;:&nbsp;</lable> <input type="text" name="s.stuSchLength"
											id="stuSchLength"
											value="<s:property value="s.stuSchLength"/>"
											disabled="disabled">
									</div> --%>
									
									<div class="control-group">
										<label class="control-label">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="s.stuNativePlace"
											id="stuNativePlace"
											value="<s:property value="s.stuNativePlace"/>">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="date" name="s.stuBirthday"
											id="stuBirthday" value="<s:property value="s.stuBirthday"/>">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="s.stuPhone"
											id="stuPhone" value="<s:property value="s.stuPhone"/>">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">宿舍电话&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="s.stuDomiPhone"
											id="stuDomiPhone"
											value="<s:property value="s.stuDomiPhone"/>">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="email" name="s.stuMail"
											id="stuMail" value="<s:property value="s.stuMail"/>">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">通信地址&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="s.stuCommAddr"
											id="stuCommAddr" value="<s:property value="s.stuCommAddr"/>">
									</div>
									</div>
									<div class="control-group">
									<label class="control-label">中文介绍:</label>
									<textarea name="s.selfIntroduce" class="textarea"><s:property
											value="s.selfIntroduce" /></textarea>
								</div>
								<div class="control-group">
									<label class="control-label">英文介绍:</label>
									<textarea name="s.selfEngIntroduce" class="textarea"><s:property
											value="s.selfEngIntroduce" /></textarea>
								</div>
									<div class="control-group">
									<div class="controls">
										<input type="submit" class="btn" value="提  交" />
									</div>
								</div>
								</form>
							<!-- 学生基本信息完 -->
							<!-- 自我介绍 -->
							
							<!-- 自我介绍完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<!-- 模态框，用于修改密码 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改密码</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Information_Modify_modifyPassword" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data"
						onsubmit="javascript:return isEmpty()">
						<div class="control-group">
							<label class="control-label">新密码：</label>
							<div class="controls">
								<input id="input-password1" type="password" name="password">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">确认新密码：</label>
							<div class="controls">
								<input id="input-password2" type="password">
							</div>
						</div>
						<div class="div-btn">
							<input type="submit" value="提交" class="btn">
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于修改密码完 -->
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88-41+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
