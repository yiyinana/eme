package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.Course;

public interface TeacherCourseService {

	List<Course> selectByNumAndTerm(String tchrNum);

}
