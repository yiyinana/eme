package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.CourseSpecificContent;

public interface CourseSpecificContentDao {
	
	boolean addSpecificContent(CourseSpecificContent csc);
	
	boolean updateSpecificContent(CourseSpecificContent csc);
	
	List<CourseSpecificContent> selectByCourse(String cursNum);
	
	List<CourseSpecificContent> selectCursSpecificContentByCursId(Integer id);
	
	boolean deleteByCursId(Integer cursId);
}