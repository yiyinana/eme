package cn.xidian.dao;

import java.util.List;
import cn.xidian.entity.CourseReferenceBooks;

public interface CourseReferenceBooksDao {

	boolean addReferenceBooks(CourseReferenceBooks courseReferenceBooks);
	
	boolean updateReferenceBooks(CourseReferenceBooks courseReferenceBooks);
	
	List<CourseReferenceBooks> selectByCourse(Integer cursId);
	
	boolean deleteByCursId(Integer cursId);
}