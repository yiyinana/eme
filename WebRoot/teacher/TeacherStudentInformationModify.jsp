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
						<!-- 修改教师基本信息 -->
						<div class="div-inf-bar">
							<label>修改学生信息</label>
						</div>
						<form action="TeacherStudent_List_1_modifyBySchNum" method="post"
							class="form-horizontal form-add" enctype="multipart/form-data">
							<div class="control-group">
								<label class="control-label">姓名：</label>
								<div class="controls">
									<input type="text" name="s.stuName"
										value="<s:property value="s.stuName"/>">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学号：</label>
								<div class="controls">
									<input type="text" name="s.stuSchNum"
										value="<s:property value="s.stuSchNum"/>">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">性别：</label>
								<div class="controls">
									<input value="<s:property value="s.stuGender" />"
										style="display: none" id="hiddenGender" /> <select
										name="s.stuGender">
										<option id="true" value="true">男</option>
										<option id="false" value="false">女</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">民族：</label>
								<div class="controls">
									<input type="text" name="s.stuNation"
										value="<s:property value="s.stuNation"/>">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">入学日期：</label>
								<div class="controls">
									<input type="text" name="s.stuAttendDate"
										value="<s:property value="s.stuAttendDate"/>">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班级：</label>
								<div class="controls">
									<input value="<s:property value="s.clazz.claId" />"
										style="display: none" id="hiddenClazz" /> <select
										name="clazzId">
										<s:iterator value="allClazz" var="c">
											<option id="<s:property value="#c.claId" />"
												value="<s:property value="#c.claId" />"><s:property
													value="#c.claName" /></option>
										</s:iterator>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学制：</label>
								<div class="controls">
									<input type="text" name="s.stuSchLength"
										value="<s:property value="s.stuSchLength"/>">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">证件号码：</label>
								<div class="controls">
									<input type="text" name="s.stuIdentNum"
										value="<s:property value="s.stuIdentNum"/>">
								</div>
							</div>
							<!-- 上传头像 -->
							<div class="control-group">
								<label class="control-label">上传头像：</label>
								<div class="controls">
									<a class="btn btn-file">选择文件<input type="file" name="file"
										id="filename"></a><input name="uploadUrl" value="/stuImg"
										style="display: none" />
								</div>
							</div>
							<!-- 上传头像 -->
							<div class="margin12">
								<input type="submit" value="提交" class="btn"
									onclick="return check(this.form)">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	<script type="text/javascript" src="js/jquery1.12.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>

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
	<script type="text/javascript">
		var msg = "${requestScope.Message}";
		if (msg != "") {
			alert(msg);
		}
	<%request.removeAttribute("Message");%>
		//显示后将request里的Message清空，防止回退时重复显示。

		$(function() {
			var selectedClaId = $("#hiddenClazz").attr("value");
			var selectedGenId = $("#hiddenGender").attr("value");
			$("#" + selectedClaId).attr("selected", true);
			$("#" + selectedGenId).attr("selected", true);
		});

		function check(form) {
			var schNum = document.getElementById("input-number").value;
			var name = document.getElementById("input-name").value;
			var identify = document.getElementById("input-identify").value;
			var errorStr1 = "";
			var errorStr2 = "";
			if (schNum == "") {
				errorStr1 += "学号";
			}
			if (name == "") {
				errorStr1 += "姓名";
			}
			if (identify.length != 18) {
				errorStr2 += "请输入18位身份证号。";
			}

			if (errorStr1 != "") {
				alert(errorStr1 + "不能为空！");
				return false;
			}
			if (errorStr2 != "") {
				alert(errorStr2);
				return false;
			}

			var filename = document.getElementById("filename").value;
			if (filename == "") {
				alert("请选择头像！");
				return false;
			}
			var index1 = filename.lastIndexOf(".");
			var index2 = filename.length;
			var postf = filename.substring(index1 + 1, index2);//后缀名  
			if (postf != "jpg") {
				alert("您选择的文件格式不正确！");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
