package cn.xidian.service;

import java.util.List;
import java.util.Set;
import cn.xidian.entity.Course;
import cn.xidian.entity.StudentCourse;
import cn.xidian.web.bean.StuCursLimits;

public interface StudentCourseService {

	List<Course> selectByNum(String stuNum);

	List<StudentCourse> selectByNumTerm(String stuNum, String currTerm);

	Set<StudentCourse> selectStuCursLimits(StuCursLimits limits);
}
