package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.CourseTeachingMode;

public interface AdminCourseTeachingModeService {

	boolean addTchingModeByCursId(List<CourseTeachingMode> ctms,Integer cursId);
	
	List<CourseTeachingMode> selectCursTchingMode(String cursNum);
	
	List<CourseTeachingMode> selectTchingModeByCursId(Integer id);
	
	boolean updateByCursId(List<CourseTeachingMode> ctms, Integer cursId);
}