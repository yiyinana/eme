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

</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/teacher_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span12 div-content-white-bgr">
					<!-- 指标点列表 -->
					<div class="div-inf-tbl">
						<form action="TeacherCourse_Manager_2_selectEvaluatedPointByYear"
							method="post">
							<div class="div-select-short">
								<input type="text" class="selectGrade" style="display:none"
									value="<s:property value="grade" />" /> 年级&nbsp;&nbsp;<select
									id="year" name="grade">
								</select>
							</div>
							<input class="btn" type="submit" value="查询">
						</form>
						<div class="div-tbl-title"></div>
						<table class="table table-bordered">
							<tr>
								<th style="width:40%">指标点</th>
								<th>相关教学课程</th>
								<th>权重值</th>
								<th>课程评价值</th>
								<th>指标点达成度</th>
							</tr>
							<s:iterator value="majorTargetValues" var="mtv">
								<s:iterator
									value="gradeCoursePoints.{?#this.point.indPointId==#mtv.point.indPointId}"
									var="gcp" status="s">
									<tr class="<s:property value="#mtv.point.indPointId" />">
										<td  class="left"><s:property value="#mtv.point.indPointNum" /> <s:property
												value="#mtv.point.indPointContent" /></td>
										<td><s:property value="#gcp.course.cursName" /></td>
										<td><s:property value="#gcp.cursPower" /></td>
										<td class="value"><s:property value="#gcp.cursEvaValue" /></td>
										<td class="value"><s:property value="#mtv.value" /></td>
									</tr>
								</s:iterator>
							</s:iterator>
						</table>
					</div>
					<!-- 指标点列表完 -->
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
		$(".container").css("min-height",
				$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
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
			$termSelect.append($Option2).append($Option3).append($Option4)
					.append($Option5).append($Option6).append($Option7).append(
							$Option8).append($Option9);
			var option = document.getElementsByTagName("option");
			for (var i = 0; i < option.length; i++) {
				if (option[i].value == selectGrade) {
					option[i].setAttribute("selected", "true");
				}
			}

			Array.prototype.unique = function() {//数组去重函数
				var res = [];
				var json = {};
				for (var i = 1; i < this.length; i++) {//从第二个开始，第一个是表头，class未定义
					if (!json[this[i]]) {
						res.push(this[i]);
						json[this[i]] = 1;
					}
				}
				return res;
			};

			//将表格中所有tr的class属性值找出来并去除重复的（tr属性已设置为每一行对应的指标点编号）
			var $tr = $("tr");
			var $class = [];
			for (var i = 0; i < $tr.length; i++) {
				$class[i] = $tr.eq(i).attr("class");
			}
			$class = $class.unique();//去掉重复的
			var grade = $(".selectGrade").val();//表格有内容时显示表格的标题
			if ($class.length == 0 && grade == "") {
			}
			else if ($class.length == 0 && grade != "") {
				alert("对不起，未找到相关记录！");
			} else {
				$(".div-tbl-title").html(grade + "级“机械设计制造及其自动化”专业毕业要求达成评价");
				for (var j = 0; j < $class.length; j++) {
					var $trs = $("." + $class[j]);
					console.log($trs.length);
					for (var k = 1; k < $trs.length; k++) {
						$trs.eq(k).find("td").first().css("display", "none");
						$trs.eq(k).find("td").last().css("display", "none");
					}
					$trs.eq(0).find("td").first().attr("rowspan", $trs.length);
					$trs.eq(0).find("td").last().attr("rowspan", $trs.length);
				}

				//格式化表格中的数据
				var value = document.getElementsByClassName("value");
				for (var i = 0; i < value.length; i++) {
					value[i].innerHTML = parseFloat(value[i].innerHTML)
							.toFixed(3);
				}
			}
		});
	</script>

</body>
</html>
