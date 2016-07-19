package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.TeachingTarget;

public interface TeachingTargetDao {

	boolean addTchingTarget(TeachingTarget targrt);
		
	boolean updateTchingTarget(TeachingTarget target);
		
	List<TeachingTarget> selectByCursId(Integer cursId);
	
	boolean deleteTchingTarget(TeachingTarget targrt);
	
}