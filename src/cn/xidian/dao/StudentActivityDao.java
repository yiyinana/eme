package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.StudentActivity;

public interface StudentActivityDao {

	boolean deleteById(Integer id);
	
	boolean add(StudentActivity activity);
	
	List<StudentActivity> selectByStuNum(String stuNum);
	
}
