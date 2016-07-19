package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.CoursePoint;
import cn.xidian.entity.IndicatorPoint;

public interface CoursePointService {
	
	boolean modifyByCursId(List<IndicatorPoint> points,Integer cursId);
	
	List<IndicatorPoint> selectPointByCursId(Integer cursId);
	
	List<CoursePoint> selectByCursId(Integer cursId);
	
	List<CoursePoint> selectByPointId(Integer pointId);

	//boolean updateCursPowerByCursPointId(List<CoursePoint> cursPoints);
	
	//List<CoursePoint> selectByGradeAndPointId(Integer pointId,String grade);
}
