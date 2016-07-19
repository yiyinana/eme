package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.Clazz;

public interface ClazzService {

    Clazz selectByName(String clazzName);
	
	List<Clazz> findAllCla();
}
