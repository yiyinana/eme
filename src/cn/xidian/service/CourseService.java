package cn.xidian.service;


import java.util.List;

import cn.xidian.entity.Course;
import cn.xidian.entity.TeachingTargetEvaluate;

public interface CourseService {
	
	/*boolean addTchingTargetByNum(List<TeachingTarget> targrts);*/
	
	boolean addCursBasicInf(Course course);
	
	Course findByNum(String cursNum);
	
	Course findByName(String cursName);
	
	Course findById(Integer cursId);
	
	boolean updateByAdmin(Course course);
	
	List<TeachingTargetEvaluate> selectByCursNameAndGrade(String cursName,String grade);
	
	List<Course> selectByCurTerm();
	
	boolean caculateCursTargetValueByCursIdAndGrade(Integer cursId,String grade);
	
	boolean updateByTchr(Course course);
	
	boolean updateCourseNote(Course course);
}
