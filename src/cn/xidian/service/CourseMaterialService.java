package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.CourseMaterial;

public interface CourseMaterialService {

	List<CourseMaterial> findByCursId(Integer cursId);
	
	boolean updateMaterialByCursId(List<CourseMaterial> materials,Integer cursId);
	
}
