<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>专业达成评价</title>
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
	$(function() {
		var selectGrade = $(".selectGrade")[0].value;
		var $termSelect = $("#year");
		var d = new Date();
		var year = d.getFullYear();//获取当前年份

		var $Option2 = $("<option value='" + (year - 3) + "'>" + (year - 3)
				+ "</option>");
		var $Option3 = $("<option value='" + (year - 2) + "'>" + (year - 2)
				+ "</option>");
		var $Option4 = $("<option value='" + (year - 1) + "'>" + (year - 1)
				+ "</option>");
		var $Option5 = $("<option value='" + (year) + "'>" + (year)
				+ "</option>");
		var $Option6 = $("<option value='" + (year + 1) + "'>" + (year + 1)
				+ "</option>");
		var $Option7 = $("<option value='" + (year + 2) + "'>" + (year + 2)
				+ "</option>");
		var $Option8 = $("<option value='" + (year + 3) + "'>" + (year + 3)
				+ "</option>");
		var $Option9 = $("<option value='" + (year + 4) + "'>" + (year + 4)
				+ "</option>");
		$termSelect.append($Option2).append($Option3).append($Option4).append(
				$Option5).append($Option6).append($Option7).append($Option8)
				.append($Option9);
		var option = document.getElementsByTagName("option");
		for (var i = 0; i < option.length; i++) {
			if (option[i].value == selectGrade) {
				option[i].setAttribute("selected", "true");
			}
		}
	});
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
							<!-- 指标点列表 -->
							<div class="div-inf-tbl">
									<form action="TeacherCourse_Manager_0_selectEvaluatedCursByYear"
										method="post">
										<div class="div-select-short">
											<input type="text" class="selectGrade" style="display:none"
												value="<s:property value="grade" />" /> 年级&nbsp;&nbsp;<select
												id="year" name="grade">
											</select>
										</div>
										<input class="btn" type="submit" value="查询">
									</form>
								<table class="table table-bordered">
									<tr>
										<td class="classify"><p>公共基础课</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='公共基础课'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
									<tr>
										<td><p>学科基础课</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='学科基础课'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
									<tr>
										<td><p>专业核心课</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='专业核心课'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
									<tr>
										<td><p>专业选修课</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='专业选修课'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
									<tr>
										<td><p>实习、实践类课程</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='实习、实践类课程'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
									<tr>
										<td><p>基础素质培养课</p></td>
										<td class="course"><s:iterator
												value="courses.{?#this.cursProperty=='基础素质培养课'}" var="c">
												<a
													href="Teacher_CourseEvaluate_Result_selectClazzCursTargetValueByGrade?grade=<s:property value="grade" />&cursId=<s:property value="cursId" />"><s:property
														value="#c.cursName" /></a>
											</s:iterator></td>
									</tr>
								</table>
							</div>
							<!-- 指标点列表完 -->
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
		});
	</script>
</body>
</html>
