package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.CoursePoint;

public interface CoursePointDao {

	List<CoursePoint> selectByCursId(Integer cursId);
	
	boolean delete(CoursePoint point);
	
	boolean add(CoursePoint point);
		
	List<CoursePoint> selectByPointId(Integer pointId);
	
	//List<CoursePoint> selectByGradeAndPointId(Integer pointId,String grade);
	
}
