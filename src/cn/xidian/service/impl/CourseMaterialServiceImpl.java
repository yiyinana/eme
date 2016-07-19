package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseMaterialDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.CourseMaterial;
import cn.xidian.service.CourseMaterialService;

@Component
public class CourseMaterialServiceImpl implements CourseMaterialService{
	
	private CourseMaterialDao courseMaterialDao;

	@Resource(name = "courseMaterialDaoImpl")
	public void setCourseMaterialDao(CourseMaterialDao courseMaterialDao) {
		this.courseMaterialDao = courseMaterialDao;
	}
	@Override
	public List<CourseMaterial> findByCursId(Integer cursId) {
		return courseMaterialDao.findByCursId(cursId);
	}

	@Override
	public boolean updateMaterialByCursId(List<CourseMaterial> materials,
			Integer cursId) {
		Course course = new Course();
		course.setCursId(cursId);
		courseMaterialDao.deleteByCursId(cursId);
		for(int i=0;i<materials.size();i++){
			CourseMaterial material = new CourseMaterial();
			material = materials.get(i);
			material.setCourse(course);
			courseMaterialDao.save(material);
		}
		return true;
	}

}
