package cn.xidian.dao;

import java.util.List;
import cn.xidian.entity.StudentCourse;

public interface StudentCourseDao {

	List<StudentCourse> findByNum(String stuNum);

	List<StudentCourse> findByNumAndTerm(String stuNum, String currTerm);

	List<StudentCourse> findByTchrNum(String tchrNum);

	List<StudentCourse> findByClaName(String claName);

	List<StudentCourse> findByCursName(String cursName);

	List<StudentCourse> findAll();
	
	List<StudentCourse> findByLimit(String claName, String curName,
			String tchrSchNum);

	List<StudentCourse> findByLimitAllClazz(String curName, String tchrSchNum);
	
	List<StudentCourse> findByCursId(Integer cursId);
	
	StudentCourse selectByCursIdAndStuId(Integer cursId,Integer stuId);
	
	boolean add(StudentCourse sc);
	
	boolean delete(StudentCourse sc);
	
}
