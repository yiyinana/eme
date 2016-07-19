package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.GraduateRequirementDao;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.service.GraduateRequirementService;

@Component("graduateRequirementServiceImpl")
public class GraduateRequirementServiceImpl implements GraduateRequirementService{

	private GraduateRequirementDao graduateRequirementDao;
	@Resource(name="graduateRequirementDaoImpl")
	public void setGraduateRequirementDao(
			GraduateRequirementDao graduateRequirementDao) {
		this.graduateRequirementDao = graduateRequirementDao;
	}


	@Override
	public List<GraduateRequirement> selectAll() {
		return graduateRequirementDao.selectAllGraReq();
	}

}
