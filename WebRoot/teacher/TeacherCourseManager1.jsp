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
	/* 数字格式化 */
	$(function() {
		var valueTd = document.getElementsByClassName("value");
		for (var i = 0; i < valueTd.length; i++) {
			if (valueTd[i].innerHTML != "") {
				valueTd[i].innerHTML = parseFloat(valueTd[i].innerHTML)
						.toFixed(3);
			}
		}
	});
	$(function(){
		var value = document.getElementsByClassName("value");
		for(var i=0;i<value.length;i++){
			value[i].innerHTML = parseFloat(value[i].innerHTML).toFixed(3);
		}
	});
	
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
		$termSelect.append($Option2).append($Option3).append(
				$Option4).append($Option5).append($Option6).append($Option7)
				.append($Option8).append($Option9);
		var option = document.getElementsByTagName("option");
		for(var i=0;i<option.length;i++){
			if(option[i].value == selectGrade){
				option[i].setAttribute("selected","true");
			}
		}
	});
	/* 验证输入数据是否符合要求 */
	function validate() {
		//input若为空则置零
		var input = document.getElementsByClassName("valueInput");
		var total = 0.0;
		for (var i = 0; i < input.length; i++) {
			if (input[i].value.toString().trim().length == 0)
				input[i].value = 0;
			total += parseFloat(input[i].value);
		}
		if (total != 1.0) {
			alert("权重值之和必须为1");
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
							<!-- 指标点列表 -->
							<div class="div-inf-tbl">
								<form action="TeacherCourse_Manager_1_selectGradeCursPointByPointIdAndGrade"
									method="post">
									<div class="div-select-short">
												<input type="text" class="selectGrade" style="display:none" value="<s:property value="grade" />" />
												年级&nbsp;&nbsp;<select id="year" name="grade">
												</select>
											</div>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th class="width24">毕业要求</th>
												<th>指标点</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="indicatorPoints" status="s">
												<tr>
													<td><label
														title="<s:property value="requirement[#s.index].graReqContent" />"><s:property
																value="requirement[#s.index].graReqTitle" /></label></td>
													<td class="indPointList"><s:iterator value="top"
															id="inner">
															<label
																title="<s:property value="#inner.indPointContent" />"><input
																type="radio" name="indPointId"
																value="<s:property value="#inner.indPointId"/>">
																<s:property value="#inner.indPointNum" /></label>
														</s:iterator></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<div class="div-btn">
										<input type="submit" value="指标点达成评价" class="btn">
									</div>
								</form>
								<form
									action="TeacherCourse_Manager_1_updateCursPowerByGradeCursPointIdAndGrade"
									method="post" onsubmit="javascript:return validate()">
									<input type="text" name="grade" style="display:none" value="<s:property value="grade" />" />
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th class="left" colspan="3">指标点<s:property
														value="gradeCoursePoints[0].point.indPointNum" />:<s:property
														value="gradeCoursePoints[0].point.indPointContent" /></th>
											</tr>
											<tr>
												<th>支撑课程</th>
												<th>评价值</th>
												<th>权重设置</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="gradeCoursePoints" var="gcp" status="s">
												<tr>
													<td><input name="gradeCoursePoints[<s:property value="#s.index"/>].course.cursId" value="<s:property value="#gcp.course.cursId"/>" style="display:none"/><s:property value="#gcp.course.cursName"/></td>
													<td class="value"><s:property value="#gcp.cursEvaValue" /></td>
													<td><input class="border0 valueInput"
														value="<s:property value="#gcp.cursPower"/>"
														name="gradeCoursePoints[<s:property value="#s.index"/>].cursPower" />
														<input value="<s:property value="#gcp.gcpId"/>"
														class="hidden"
														name="gradeCoursePoints[<s:property value="#s.index"/>].gcpId" />
														<input
														value="<s:property value="#gcp.point.indPointId"/>"
														class="hidden"
														name="gradeCoursePoints[<s:property value="#s.index"/>].point.indPointId" />
														<input value="<s:property value="#gcp.cursEvaValue"/>"
														class="hidden"
														name="gradeCoursePoints[<s:property value="#s.index"/>].cursEvaValue" />
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<div class="div-btn">
										<input type="submit" value="提交" class="btn">
									</div>
								</form>
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
