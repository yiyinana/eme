package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.TeachingTargetEvaluateDao;
import cn.xidian.entity.TeachingTargetEvaluate;
import cn.xidian.service.TeachingTargetEvaluateService;

@Component
public class TeachingTargetEvaluateServiceImpl implements
		TeachingTargetEvaluateService {

	private TeachingTargetEvaluateDao teachingTargetEvaluateDao;

	@Resource(name = "teachingTargetEvaluateDaoImpl")
	public void setTeachingTargetEvaluateDao(
			TeachingTargetEvaluateDao teachingTargetEvaluateDao) {
		this.teachingTargetEvaluateDao = teachingTargetEvaluateDao;
	}

	@Override
	public List<TeachingTargetEvaluate> selectByCursAndClazz(String cursName,
			 String claName) {
		List<TeachingTargetEvaluate> ttelist = new LinkedList<TeachingTargetEvaluate>();
		ttelist = teachingTargetEvaluateDao.selectByCursAndClazz(cursName,claName);
		return ttelist;
	}

}
