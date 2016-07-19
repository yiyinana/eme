package cn.xidian.service;

import java.util.Set;

import cn.xidian.entity.Course;
import cn.xidian.web.bean.AdminCursLimits;

public interface AdminCourseService {

	Set<Course> selectAllCurs();
	
	Set<Course> selectCursLimits(AdminCursLimits limits);
			
	boolean deleteByCursId(Integer cursId);
		
	boolean updateCursNoteById(Course course);
	
}
