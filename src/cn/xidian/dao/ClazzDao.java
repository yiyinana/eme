package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.Clazz;

public interface ClazzDao {

	Clazz selectByName(String clazzName);
	
	List<Clazz> findAllCla();
	
	List<Clazz> selectByGrade(String clazz);
	
}
