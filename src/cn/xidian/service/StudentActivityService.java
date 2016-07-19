package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.StudentActivity;

public interface StudentActivityService {

	boolean deleteById(Integer id);
	
	boolean add(StudentActivity activity);
	
	List<StudentActivity> selectByStuNum(String stuNum);
	
}
