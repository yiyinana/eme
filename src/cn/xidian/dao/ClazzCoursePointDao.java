package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.ClazzCoursePoint;

public interface ClazzCoursePointDao {

	boolean addByCursNum(ClazzCoursePoint point);
	
	List<ClazzCoursePoint> selectByCursNum(String cursNum);
	
	boolean add(ClazzCoursePoint point);
	
	List<ClazzCoursePoint> selectByCursId(Integer cursId);
	
	List<ClazzCoursePoint> selectByCursAndClazzId(Integer cursId,Integer clazId);
	
	boolean deleteById(ClazzCoursePoint coursePoint);
	
	List<ClazzCoursePoint> findByCursNameAndTerm(String cursName);
	
	boolean deleteByCursId(Integer cursId);
	
	List<ClazzCoursePoint> findByCursAndClazz(String cursName,
			String claName);
	
}
