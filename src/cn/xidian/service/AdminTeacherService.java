package cn.xidian.service;

import java.util.List;
import java.util.Set;

import cn.xidian.dao.TeacherDao;
import cn.xidian.entity.Teacher;
import cn.xidian.web.bean.AdminTchrLimits;

public interface AdminTeacherService {

	boolean addTeacher(Teacher teacher);

	boolean deleteTeacher(String schNum);

	boolean updateTeacher(Teacher teacher);

	Set<Teacher> selectUnderLimits(AdminTchrLimits limits);

	void setTeacherDao(TeacherDao teacherDao);

	boolean selectTchrBySchNum(String schNum);
	
	List<Teacher> selectAllTchrs();
	
	Teacher selectTeacherBySchNum(String schNum);
	
	boolean addTchrExcel(String path);
	
	boolean addTchrCursExcel(String path);
	
	List<Teacher> selectAllManagers();
	
	boolean updateManager(List<Integer> managerIds);
}