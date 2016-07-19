package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.ClazzCoursePoint;
import cn.xidian.entity.IndicatorPoint;

public interface ClazzCoursePointService {

	// boolean addByCursNum(List<IndicatorPoint> points,String cursNum);

	// List<CoursePoint> selectCursNum(String cursNum);

	List<IndicatorPoint> selectPointByCursId(Integer id);

	List<ClazzCoursePoint> selectBycursNameAndTerm(String cursName);

	boolean addByCursId(List<IndicatorPoint> points, Integer cursId);

	boolean modifyByCursId(List<IndicatorPoint> points, Integer cursId);

	List<ClazzCoursePoint> selectByCursAndClazz(String cursName,String claName);
}
