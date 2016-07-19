package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.CourseSpecificContent;

public interface AdminCourseSpecificContentService {
	
	boolean addSpecificContentByCursId(List<CourseSpecificContent> sc,Integer cursId);
			
	List<CourseSpecificContent> selectCursSpecificContentByCursId(Integer id);
	
	boolean updateByCursId(List<CourseSpecificContent> sc, Integer cursId);
}