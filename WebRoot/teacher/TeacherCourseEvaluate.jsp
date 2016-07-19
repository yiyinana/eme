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
	
	function stateValidte() {//判断若存在未评定班级，则不能进行课程达成评定
		var tbody = document.getElementById("isEvaluate");
		var tr = tbody.getElementsByTagName("tr");
		if(tr.length == 0){
			alert("没有可以参与评价的数据");
			return false;
		}
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
							<!-- 成绩评定与查询 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>课程达成评价</label>
								</div>
								<div class="div-inf-tbl">
									<form action="Teacher_Course_Evaluate_selectClazzCursTargetValueByGrade?cursId=<s:property value="cursId" />" method="post">
											<div class="div-select-short">
												<input type="text" class="selectGrade" style="display:none" value="<s:property value="grade" />" />
												年级&nbsp;&nbsp;<select id="year" name="grade">
												</select>
											</div>
											<input class="btn" type="submit" value="查询">
										</form>
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>课程</th>
												<th>班级</th>
												<th>上传人</th>
												<th>上传时间</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody id="isEvaluate">
											<s:iterator value="cte" var="c">
												<tr>
													<td><s:property value="#c.course" /></td>
													<td><s:property value="#c.clazz" /></td>
													<td><s:property value="#c.teacher" /></td>
													<td><s:property value="#c.date" /></td>
													<td class="state"><s:property value="#c.state" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<a class="lable-add"
										href="Teacher_Course_Evaluate_caculateCursTargetValue?cursId=<s:property value="cursId" />&grade=<s:property value="grade" />"
										onclick="javascript:return stateValidte()">课程达成评定</a>
								</div>

								<div class="div-inf-tbl">
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
													<td><s:property value="#cp.point.indPointNum"/>.<s:property value="#cp.point.indPointContent"/></td>
													<td class="value"><s:property value="#cp.cursEvaValue"/></td>
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
		});
	</script>
</body>
</html>
