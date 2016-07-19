package cn.xidian.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.AdminDao;
import cn.xidian.entity.Admin;
import cn.xidian.exception.AdminExistsException;
import cn.xidian.exception.AdminNotExistsException;
import cn.xidian.service.AdminService;
import cn.xidian.utils.ServiceUtils;

@Component
public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;

	@Resource(name = "adminDaoImpl")
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean loginValidate(String schNum, String pwd) {
		try {
			return adminDao.findBySchNumAndPwd(schNum, ServiceUtils.md5(pwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin selectInfBySchNum(String schNum) {
		return adminDao.findBySchNum(schNum);
	}

	@Override
	public List<Admin> selectAllAdmin() {
		return adminDao.findAllAdmins();
	}

	@Override
	public boolean deleteAdminBySchNum(String schNum) {
		Admin admin = adminDao.findBySchNum(schNum);
		if (admin == null) {
			throw new AdminNotExistsException("要删除的管理员不存在");
		}
		return adminDao.deleteAdminBySchNum(schNum);
	}

	@Override
	public boolean updateAdminBySchNumAndPwd(String schNum, String pwd) {
		Admin admin = adminDao.findBySchNum(schNum);
		if (admin == null) {
			throw new AdminNotExistsException("该管理员不存在");
		}
		try {
			admin.setAdminPwd(ServiceUtils.md5(pwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return adminDao.updateAdmin(admin);
	}

	@Override
	public boolean addAdmin(Admin admin) {
		String pwd = admin.getAdminPwd();
		try {
			admin.setAdminPwd(ServiceUtils.md5(pwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		Admin adminTemp = adminDao.findBySchNum(admin.getAdminSchNum());
		if (adminTemp != null) {
			throw new AdminExistsException("该管理员已存在，请勿重复添加");
		}
		return adminDao.addAdmin(admin);
	}

}
