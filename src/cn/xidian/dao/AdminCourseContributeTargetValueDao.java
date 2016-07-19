package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.ContributeTarget;

public interface AdminCourseContributeTargetValueDao {

	boolean saveContributeTargetValue(ContributeTarget ct);
	
	List<ContributeTarget> selectByTargetId(Integer cursId);
	
	boolean deleteByTargetId(Integer tarId);
	
}
