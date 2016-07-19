package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.ContributeTarget;

public interface AdminCourseContributeTargetValueService {

	boolean saveContributeTargetValue(List<ContributeTarget> contributeTargets);
	
	List<ContributeTarget> selectByCursId(Integer cursId);
	
	boolean updateContributeTargetValue(List<ContributeTarget> contributeTargets,Integer cursId);
}
