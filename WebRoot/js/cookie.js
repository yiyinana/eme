
var allcookies = document.cookie;
var cookie = allcookies.indexOf("schNum");
if(cookie == ""){
	alert("请先登录！");
	location.href="admin/AdminLogin.jsp";
}