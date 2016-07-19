package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.TeacherExperiment;

public interface TeacherExperimentDao {

	boolean add(TeacherExperiment experiment);
	
	List<TeacherExperiment> selectByTchrNum(String tchrNum);
	
	boolean deleteById(Integer expId);
	
}
