package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.TeachingTarget;

public interface TeachingTargetService {
	
	boolean addTchingTargetByCursId(List<TeachingTarget>targets,Integer cursId);
	
	boolean updateTchingTargetValue(List<TeachingTarget>targets);
		
	List<TeachingTarget> selectByCursId(Integer cursId);
	
	List<TeachingTarget> selectByCursName(String cursName);
	
	boolean updateByCursId(List<TeachingTarget> targets, Integer cursId);
	
	boolean updateValue(List<TeachingTarget> targets);
}