package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.MajorTargetValue;

public interface MajorTargetValueService {
	
	List<MajorTargetValue> selectByGrade(String grade);
}
