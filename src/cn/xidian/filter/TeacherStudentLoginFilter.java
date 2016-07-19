package cn.xidian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xidian.entity.User;

public class TeacherStudentLoginFilter implements Filter{

	public void destroy() {
		  //过滤器销毁，一般是释放资源
		 }
		 /**
		  * 某些url需要登陆才能访问（session验证过滤器）
		  */
		 public void doFilter(ServletRequest arg0, ServletResponse arg1,
		   FilterChain arg2) throws IOException, ServletException {
		  HttpServletRequest request=(HttpServletRequest)arg0;
		  HttpServletResponse response=(HttpServletResponse)arg1;
		  HttpSession session=request.getSession();
		  if((User)session.getAttribute("tUser")==null){
		   String errors = "您还没有登录，或者session已过期。请先登陆!";
		   request.setAttribute("Message", errors);
		   request.getRequestDispatcher("/login_frame.jsp").forward(request, response);
		  }else{
		   arg2.doFilter(request, response);
		  }
		 }
		 public void init(FilterConfig arg0) throws ServletException {
		           //初始化操作，读取web.xml中过滤器配置的初始化参数，满足你提的要求不用此方法
		 }
}
