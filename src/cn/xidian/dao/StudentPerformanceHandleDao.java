package cn.xidian.dao;

import cn.xidian.entity.StudentCourse;

public interface StudentPerformanceHandleDao {

	boolean addStuCurs(StudentCourse studentCourse);
	
	boolean updateStuCurs(StudentCourse studentCourse);
	
}
