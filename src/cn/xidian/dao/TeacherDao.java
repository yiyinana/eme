package cn.xidian.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherCourse;

public interface TeacherDao {

	void setSessionFactory(SessionFactory sessionFactory);

	Teacher findBySchNum(String tchrSchNum);

	Set<Teacher> findByName(String name);

	Set<Teacher> findByTitle(String title);

	boolean updateByTeacher(Teacher teacher);

	boolean findBySchNumAndPwd(String tchrSchNum, String tchrPwd);

	boolean addByAdmin(Teacher teacher);

	boolean deleteByAdmin(Integer id);

	boolean updateByAdmin(Teacher teacher);

	List<Teacher> selectAllTchrs();
	
	boolean addTchrCurs(TeacherCourse teacherCourse);
	
	boolean modifyPassword(String num,String pwd);
	
	List<Teacher> selectAllManagers();
	
	boolean addManager(Integer id);
	
	boolean deleteManager(Integer id);

}