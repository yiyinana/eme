package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.Admin;

public interface AdminDao {

	boolean findBySchNumAndPwd(String schNum, String pwd);

	Admin findBySchNum(String schNum);
	
	List<Admin> findAllAdmins();
	
	boolean deleteAdminBySchNum(String schNum);
	
	boolean updateAdmin(Admin admin);
	
	boolean addAdmin(Admin admin);
}