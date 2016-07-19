package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.CourseTeachingMode;

public interface CourseTeachingModeDao {

	boolean addCursTchingMode(CourseTeachingMode ctm);
	
	boolean updateCursTchingMode(CourseTeachingMode ctm);
	
	List<CourseTeachingMode> selectByCourse(String cursNum);
	
	List<CourseTeachingMode> selectTchingModeByCursId(Integer id);
	
	boolean deleteByCursId(Integer cursId);
}