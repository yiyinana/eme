package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.CourseMaterial;

public interface CourseMaterialDao {
	
	boolean deleteByCursId(Integer cursId);
	
	boolean save(CourseMaterial material);
	
	List<CourseMaterial> findByCursId(Integer cursId);

}
