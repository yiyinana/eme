package cn.xidian.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.User;
import cn.xidian.service.LoginWelcomService;
import cn.xidian.entity.Identity;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class LoginAction extends ActionSupport implements RequestAware,
		SessionAware {
	private User user;
	private String identity;
	private Map<String, Object> request;
	private Map<String, Object> session;

	private LoginWelcomService loginWelcomService;

	@Resource(name = "loginWelcomServiceImpl")
	public void setLoginWelcomService(LoginWelcomService loginWelcomService) {
		this.loginWelcomService = loginWelcomService;
	}

	public String loginValidate() {
		Identity iden = Identity.valueOf(identity.toUpperCase());
		user.setIdentity(iden);
		User u = loginWelcomService.loginValidate(user);
		if (u == null) {
			request.put("Message", "对不起，用户名或密码错误！");
			if (identity.equals("admin")) {
				return "AFalse";
			} else
				return "TSFalse";
		}
		if (u.getIdentity().toString().equals("TEACHER")) {
			session.put("tUser", u);
			return "teacher";
		} else if (u.getIdentity().toString().equals("STUDENT")) {
			session.put("tUser", u);
			return "student";
		} else if (u.getIdentity().toString().equals("ADMIN")) {
			session.put("tUser", u);
			return "admin";
		} else if (identity.equals("admin")) {
			request.put("Message", "对不起，用户名或密码错误！");
			return "AFalse";
		} else
			return "TSFalse";
	}

	public String loginOut() {
		session.remove("tUser");
		return SUCCESS;
	}

	public String adminLoginOut() {
		session.remove("tUser");
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
