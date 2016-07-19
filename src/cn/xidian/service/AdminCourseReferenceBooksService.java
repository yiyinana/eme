package cn.xidian.service;

import java.util.List;
import cn.xidian.entity.CourseReferenceBooks;

public interface AdminCourseReferenceBooksService {

	boolean addRefBookByCursId(List<CourseReferenceBooks> crbs, Integer cursId);
	
	List<CourseReferenceBooks> selectReferenceBooks(Integer cursId);
	
	boolean updateByCursId(List<CourseReferenceBooks> crbs, Integer cursId);
}