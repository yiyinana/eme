package cn.xidian.service;

import java.util.List;
import java.util.Set;

import cn.xidian.entity.Course;
import cn.xidian.entity.GradeCoursePoint;

public interface GradeCoursePointService {

	boolean updateCursPowerByGradeCursPointIdAndGrade(List<GradeCoursePoint> cursPoints,String grade);
	
	List<GradeCoursePoint> selectByPointIdAndGrade(Integer pointId,String grade);
	
	List<GradeCoursePoint> selectByCursIdAndGrade(Integer cursId,String grade);
	
	Set<Course> selectByGrade(String grade);
	
}
