package cn.xidian.dao;

import java.util.List;
import java.util.Set;

import cn.xidian.entity.Course;


public interface CourseDao {
	
	Course findByNum(String cursNum);
	
	Course findByName(String cursName);
	
	Set<Course> findAllCurs();

	boolean updateByAdmin(Course course);

	boolean addByAdmin(Course course);
			
	boolean updateCursNote(Course course);
	
	Course findByNameAndTerm(String cursName);
	
	List<Course> findByTerm();
	
	//List<Course> findByIds(List<Integer> cursId);
	
	boolean deleteByCursId(Integer cursId);
	
	Course findById(Integer cursId);
	
	boolean updateCursNoteById(Course course);
	
	List<Course> selectTchChargeCursByTchrNum(String tchrNum);
	
	boolean updateByTchr(Course course);
	
}
