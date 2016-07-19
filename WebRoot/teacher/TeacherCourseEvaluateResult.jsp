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

<title>课程达成评价</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/teaching_management.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>
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
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/tchrLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<div class="div-module-add-curs">
								<h6>
									<img class="image-path-1" src="img/circle.jpg" /> <a
										id="goBack">返回课程列表</a>
								</h6>
							</div>
							<!-- 成绩评定与查询 -->
							<div class="div-tchr-curs">
								<div class="div-inf-tbl">
									<div class="div-tbl-title">
										<s:property value="grade" />
										级《
										<s:property value="gradeCoursePoints[0].course.cursName" />
										》课程达成度评价结果
									</div>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>毕业要求指标点</th>
												<th>达成度评价值</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="gradeCoursePoints" var="cp">
												<tr>
													<td class="left"><s:property value="#cp.point.indPointNum" />.<s:property
															value="#cp.point.indPointContent" /></td>
													<td class="value"><s:property value="#cp.cursEvaValue" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 成绩评定与查询完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>

	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
			
			//返回到上一页
			document.getElementById("goBack").onclick = function() {
				history.go(-1);
			};
			
			//格式化表格中的数据
			var value = document.getElementsByClassName("value");
			for(var i=0;i<value.length;i++){
				value[i].innerHTML = parseFloat(value[i].innerHTML).toFixed(3);
			}
		});
	</script>
</body>
</html>
