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

<title>查看学生信息</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teacher_information.css" />
<link rel="stylesheet" href="css/student_information.css" />

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
							<div class="div-inf-bar">
								<label>学生信息</label>
							</div>
							<!-- 学生基本信息 -->

							<div class="div-inf-tbl">
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>学号</td>
											<td><s:property value="s.stuSchNum" /></td>
											<td>姓名</td>
											<td><s:property value="s.stuName" /></td>
											<td class="img-stu" rowspan=4><img
												src="stuImg/<s:property value="s.stuSchNum"/>.jpg" /></td>
										</tr>
										<tr>
											<td>性别</td>
											<td><s:if test="%{s.stuGender==true}">男</s:if> <s:else>女</s:else></td>
											<td>生日</td>
											<td><s:property value="s.stuBirthday" /></td>
										</tr>
										<tr>
											<td>籍贯</td>
											<td><s:property value="s.stuNativePlace" /></td>
											<td>民族</td>
											<td><s:property value="s.stuNation" /></td>
										</tr>
										<tr>
											<td>身份证</td>
											<td><s:property value="s.stuIdentNum" /></td>
											<td>班级</td>
											<td><s:property value="s.clazz.claName" /></td>
										</tr>
										<tr>
											<td>入学日期</td>
											<td><s:property value="s.stuAttendDate" /></td>
											<td>学制</td>
											<td colspan=2><s:property value="s.stuSchLength" /></td>
										</tr>
										<tr>
											<td>手机</td>
											<td><s:property value="s.stuPhone" /></td>
											<td>宿舍电话</td>
											<td colspan=2><s:property value="s.stuDomiPhone" /></td>
										</tr>
										<tr>
											<td>邮箱</td>
											<td><s:property value="s.stuMail" /></td>
											<td>通信地址</td>
											<td colspan=2><s:property value="s.stuCommAddr" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="div-inf-tbl">
								<h5>获奖情况</h5>

								<table class="table table-bordered " id="itemStuList">
									<thead>
										<tr>
											<th>项目编号</th>
											<th>项目名称</th>
											<th>审核状态</th>
											<th>得分</th>
											<th>审核</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="items" var="i">
											<tr>
												<td><s:property value="#i.itemNum" /></td>
												<td><s:property value="#i.itemName" /></td>
												<td><s:property value="#i.itemState" /></td>
												<td><s:property value="#i.itemScore" /></td>
												<td><a
													href="TeacherStudent_Item_1_selectItemInfo?itemId=<s:property value="#i.stuItemId"/>">详情审核</a></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
							<div class="div-inf-tbl">
								<h5>自我介绍</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.selfIntroduce" /></label>
								</div>
							</div>
							<div class="div-inf-tbl">
								<h5>英文自我介绍</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.selfEngIntroduce" /></label>
								</div>
							</div>
							<!-- 学生基本信息完 -->
							<!-- 学生目标 -->
							<div class="div-inf-tbl">
								<h5>短期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.shortGoal" /></label>
								</div>
								<h5>中期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.midGoal" /></label>
								</div>
								<h5>长期目标</h5>
								<div class="div-tchr-self-intr-content">
									<label><s:property value="s.longGoal" /></label>
								</div>
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
