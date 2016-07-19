package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.Admin;

public interface AdminService {

	boolean loginValidate(String schNum, String pwd);

	Admin selectInfBySchNum(String schNum);

	List<Admin> selectAllAdmin();

	boolean deleteAdminBySchNum(String schNum);
	
	boolean updateAdminBySchNumAndPwd(String schNum, String pwd);
	
	boolean addAdmin(Admin admin);
	
}