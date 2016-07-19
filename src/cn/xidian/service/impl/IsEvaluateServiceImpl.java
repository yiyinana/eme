package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.IsEvaluateDao;
import cn.xidian.entity.IsEvaluate;
import cn.xidian.service.IsEvaluateService;

@Component("isEvaluateServiceImpl")
public class IsEvaluateServiceImpl implements IsEvaluateService{
	
	private IsEvaluateDao isEvaluateDao ;
	@Resource(name="isEvaluateDaoImpl")
	
	public void setIsevaluateDao(IsEvaluateDao isEvaluateDao) {
		this.isEvaluateDao = isEvaluateDao;
	}


	@Override
	public List<IsEvaluate> selectByCursId(Integer cursId) {
		return isEvaluateDao.findByCursId(cursId);
	}


	@Override
	public List<IsEvaluate> selectByCursIdAndGrade(Integer cursId, String grade) {
		return isEvaluateDao.selectByCursIdAndGrade(cursId,grade);
	}

}
