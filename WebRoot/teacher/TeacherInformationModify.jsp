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

<title>修改教师信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
	function validate(){
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
	function isEmpty(){
		var school = document.getElementById("input-school");
		var position = document.getElementById("input-position");
		var time = document.getElementById("input-time");
		
		if(school.value.toString().trim().length != 0 &&position.value.toString().trim().length != 0 &&time.value.toString().trim().length != 0){
			return true;
		}
		else{
			if(school.value.toString().trim().length == 0) {
				school.focus();
			}
			if(position.value.toString().trim().length == 0) {
				position.focus();
			}
			if(time.value.toString().trim().length == 0) {
				time.focus();
			}
			alert("输入框不能为空！");
			return false;
		}
	}
	function check(form) {
		var birthday = document.getElementById("tchrBirthday").value;
		if(birthday.trim()=="")return true;
	    var r=birthday.match(/^(\d{1,2})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
	    if(r==null){
	      alert("请输入格式正确的日期\n\r日期格式：yy-mm-dd\n\r例    如：68-12-25\n\r");
	    return false;
	    }
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			return true;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "img" && postf != "png" && postf != "jpg") {
			alert("您选择的文件格式不正确！");
			return false;
		}
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
							<!-- 修改教师基本信息 -->
							<div class="div-inf-bar">
								<label>修改基本资料</label>
							</div>
							<form class="form-horizontal" enctype="multipart/form-data"
								action="Teacher_Information_Modify_modifyBasicInf" method="post">
								<div class="control-group">
									<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
									<div class="controls">
										<input type="text" name="teacher.tchrName"
											value="<s:property value="teacher.tchrName"/>">
											<label class="lable-modify" data-toggle="modal"
									data-target="#myModal1">修改密码</label>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">出生年月</label>
									<div class="controls">
										<input type="text" name="teacher.tchrBirthday" id="tchrBirthday"
											value="<s:property value="teacher.tchrBirthday" />">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
									<div class="controls">
										<input type="text" name="teacher.tchrTitle" id="title"
											value="<s:property value="teacher.tchrTitle"/>">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
									<div class="controls">
										<input type="text" name="teacher.tchrPhone" id="phone"
											value="<s:property value="teacher.tchrPhone"/>">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</label>
									<div class="controls">
										<input type="text" name="teacher.tchrFax" id="fax"
											value="<s:property value="teacher.tchrFax"/>">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">电子邮件</label>
									<div class="controls">
										<input type="email" name="teacher.tchrEmail" id="email"
											value="<s:property value="teacher.tchrEmail"/>">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">办&nbsp;&nbsp;公&nbsp;&nbsp;室</label>
									<div class="controls">
										<input type="text" name="teacher.tchrOfficeAddr" id="office"
											value="<s:property value="teacher.tchrOfficeAddr"/>">
									</div>
								</div>
								<!-- <div class="control-group">
									<label class="control-label">个人主页</label>
									<div class="controls">
										<input type="text" name="" id="self_url" value="www.baidu.com">
									</div>
								</div> -->
								<div class="control-group">
							<label class="control-label">上传头像</label>
							<div class="controls">
								<a class="btn btn-file">选择文件<input type="file" name="file"
									id="filename"></a><input name="uploadUrl" value="/tchrImg"
									style="display:none" />
							</div>
						</div>
								<div class="control-group">
									<label class="control-label">自我介绍</label>
									<textarea name="teacher.tchrSelflIntroduction" class="textarea-selfIntr"><s:property
											value="teacher.tchrSelflIntroduction" /></textarea>
								</div>
								<div class="control-group">
									<div class="controls">
										<input type="submit" class="btn" value="提  交" onclick="return check(this.form)" />
									</div>
								</div>
							</form>
							<!-- 修改教师基本信息完 -->
							<!-- 修改教师学历履历 -->
							<div class="div-inf-bar">
								<label>修改学历履历信息</label>
							</div>
								<div class="div-inf-tbl">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>学校</th>
												<th>学位</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="tbody-exp">
										<s:iterator value="experiments" var="e">
											<tr>
												<td><s:property value="#e.school"/></td>
												<td><s:property value="#e.position"/></td>
												<td><s:property value="#e.time"/></td>
												<td><a href="Teacher_Information_Modify_deleteExpById?expId=<s:property value="#e.tchrExpId"/>">删除</a></td>
											</tr>
											</s:iterator>
										</tbody>
									</table>
									<label class="lable-add" data-toggle="modal" data-target="#myModal">添加</label>
								</div>
					
							<!-- 修改教师学历履历完 -->
							<!-- 修改教师主持项目 -->

							<!-- 修改教师主持项目完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<!-- 模态框，用于添加教师学历履历信息 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">添加教师学历/履历信息</h5>
				</div>
				<div class="modal-body">
					<form action="Teacher_Information_Modify_addExpByTchrNum" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data" onsubmit="javascript:return isEmpty()">
						<div class="control-group">
						<label class="control-label">学校/单位：</label>
						<div class="controls">
							<input id="input-school" type="text" name="experiment.school">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学位/职称：</label>
						<div class="controls">
							<input id="input-position" type="text" name="experiment.position">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">时间：</label>
						<div class="controls">
							<input id="input-time" type="text" name="experiment.time">
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
	<!-- 模态框，用于添加教师学历履历信息完 -->
	<!-- 模态框，用于修改密码 -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改密码</h5>
				</div>
				<div class="modal-body">
					<form action="Teacher_Information_Modify_modifyPassword" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data"
						onsubmit="javascript:return validate()">
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
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
