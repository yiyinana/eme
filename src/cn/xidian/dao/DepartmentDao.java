package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.Department;

public interface DepartmentDao {

	List<Department> selectAllDepts();
}
