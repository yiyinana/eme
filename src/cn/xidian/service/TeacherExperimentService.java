package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.TeacherExperiment;

public interface TeacherExperimentService {

	boolean addByTchrNum(TeacherExperiment experiment,String tchrNum);
	
	List<TeacherExperiment> selectByTchrNum(String tchrNum);
	
	boolean deleteById(Integer expId);
	
}
