package cn.xidian.service;

import java.util.List;

import cn.xidian.dao.TeacherDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.Teacher;

public interface TeacherService {

	public abstract void setTeacherDao(TeacherDao teacherDao);

	public abstract boolean loginValidate(String tchrSchNum, String tchrPwd);

	public abstract Teacher selectInfBySchNum(String tchrSchNum);

	public abstract boolean updateInfBySchNum(Teacher teacher);
	
	boolean modifyPassword(String tchrSchNum, String tchrPwd);
	
	List<Course> selectTchCoursesByTchrNum(String tchrNum);
	
	List<Course> selectChargeCoursesByTchrNum(String tchrNum);
	
	boolean caculateClazzTarget(String cursNum, String claName,String tchrSchNum);
	
}