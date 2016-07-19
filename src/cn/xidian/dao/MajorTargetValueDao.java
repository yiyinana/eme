package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.MajorTargetValue;

public interface MajorTargetValueDao {

	MajorTargetValue selectByPointAndTerm(String currentTerm,Integer pointId);
	
	boolean add(MajorTargetValue mtv);
	
	boolean delete(MajorTargetValue mtv);
	
	List<MajorTargetValue> selectByGrade(String grade);
}
