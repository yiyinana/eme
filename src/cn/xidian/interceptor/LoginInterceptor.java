package cn.xidian.interceptor;

import java.util.Map;  

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.xidian.entity.User;

import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String,Object> session = ctx.getSession();  
        User user = (User) session.get("tUser");  
        if (user == null || user.getSchNum().equals("")) {  
            System.out.println("test");  
            HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("Message", "您还没有登录，或者session已过期。请先登陆!");
 		   	return "tsLogin";
        }  
        
		/*
		 * invocation.invoke() 就是通知struts2接着干下面的事情 比如 调用下一个拦截器 或 执行下一个Action
		 * 就等于退出了你自己编写的这个interceptor了
		 */
        return invocation.invoke();  
	}

}
