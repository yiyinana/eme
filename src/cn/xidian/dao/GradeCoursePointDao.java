package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.GradeCoursePoint;


public interface GradeCoursePointDao {

	boolean save(GradeCoursePoint ccp);
	
	boolean delete(GradeCoursePoint ccp);
	
	List<GradeCoursePoint> selectByCursIdAndPointId(Integer cursId,Integer pointId);
	
	List<GradeCoursePoint> selectByCursIdAndGrade(Integer cursId,String grade);
	
	List<GradeCoursePoint> selectByPointId(Integer pointId);
	
	List<GradeCoursePoint> selectByPointIdAndGrade(Integer pointId,String grade);
	
	boolean updateCursPowerByCursPointIdAndGrade(GradeCoursePoint gradeCursPoints, String grade);
	
	List<GradeCoursePoint> selectByGrade(String grade);
	
	boolean deleteByCursId(Integer cursId);
	
}
