<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <script>
    	window.onload = function() {
    		document.getElementById("toMain").click(); 		
    	}
    </script> 
  </head>
  
  <body>
  	<a id="toMain" href="Major___selectMajorInf" style="display:none">登录</a><br>
 </body>
</html>
