package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.TeacherCourse;

public interface TeacherCourseDao {

	List<TeacherCourse> findByNumAndTerm(String tchrNum);

	List<TeacherCourse> findAlltchrCurs();
	
}
