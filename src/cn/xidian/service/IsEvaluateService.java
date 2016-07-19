package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.IsEvaluate;

public interface IsEvaluateService {
	
	List<IsEvaluate> selectByCursId(Integer cursId);
	
	List<IsEvaluate> selectByCursIdAndGrade(Integer cursId,String grade);
	
}
