package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.DepartmentDao;
import cn.xidian.entity.Department;
import cn.xidian.service.DepartmentService;

@Component("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService{

	DepartmentDao departmentDao;
	@Resource(name="departmentDaoImpl")
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	
	@Override
	public List<Department> selectAllDepts() {
		return departmentDao.selectAllDepts();
	}

}
