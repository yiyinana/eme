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

<title>学生基本信息</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_information.css" />
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
									<label>基本信息</label>
								</div>
								<div class="div-inf-tbl">
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>学号</td>
											<td><s:property value="s.stuSchNum"/></td>
											<td>姓名</td>
											<td><s:property value="s.stuName"/></td>
											<td class="img-stu" rowspan=4><img
												src="stuImg/<s:property value="s.stuSchNum"/>.jpg" />
											</td>
										</tr>
										<tr>
											<td>性别</td>
											<td><s:if test="%{s.stuGender==true}">男</s:if> 
										<s:else>女</s:else></td>
											<td>生日</td>
											<td><s:property value="s.stuBirthday"/></td>
										</tr>
										<tr>
											<td>籍贯</td>
											<td><s:property value="s.stuNativePlace"/></td>
											<td>民族</td>
											<td><s:property value="s.stuNation"/></td>
										</tr>
										<tr>
											<td>院系</td>
											<td><s:property value="s.dept.deptName"/></td>
											<td>班级</td>
											<td><s:property value="clazz"/></td>
										</tr>
										<tr>
											<td>入学日期</td>
											<td><s:property value="s.stuAttendDate"/></td>
											<td>学制</td>
											<td colspan=2><s:property value="s.stuSchLength"/></td>
										</tr>
										<tr>
											<td>手机</td>
											<td><s:property value="s.stuPhone"/></td>
											<td>宿舍电话</td>
											<td colspan=2><s:property value="s.stuDomiPhone"/></td>
										</tr>
										<tr>
											<td>邮箱</td>
											<td><s:property value="s.stuMail"/></td>
											<td>通信地址</td>
											<td colspan=2><s:property value="s.stuCommAddr"/></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- 学生基本信息完 -->
							<!-- 自我介绍 -->
							<div>
								<div class="div-inf-bar">
									<label>自我介绍</label>
								</div>
								<div class="row-fluid">
									<div class="span5 offset1 div-self-intr">
										<img src="img/vesion-Chinese.jpg">
										<div class="div-self-content">
											<s:property value="s.selfIntroduce"/>
										</div>
									</div>
									<div class="span5 div-self-intr">
										<img src="img/vesion-English.jpg">
										<div class="div-self-content">
											<s:property value="s.selfEngIntroduce"/>
										</div>
									</div>
								</div>
							</div>
							<!-- 自我介绍完 -->
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
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88-41+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
