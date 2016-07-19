package cn.xidian.service;

import cn.xidian.entity.User;

public interface LoginWelcomService {

	public abstract User loginValidate(User user);

}