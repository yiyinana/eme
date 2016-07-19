package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzDao;
import cn.xidian.entity.Clazz;
import cn.xidian.service.ClazzService;

@Component
public class ClazzServiceImpl implements ClazzService {

	private ClazzDao clazzDao;

	@Resource(name = "clazzDaoImpl")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	@Override
	public Clazz selectByName(String clazzName) {
		return clazzDao.selectByName(clazzName);
	}

	@Override
	public List<Clazz> findAllCla() {
		return clazzDao.findAllCla();
	}

}
