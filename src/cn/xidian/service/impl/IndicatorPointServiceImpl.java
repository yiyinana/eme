package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.GraduateRequirementDao;
import cn.xidian.dao.IndicatorPointDao;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.service.IndicatorPointService;

@Component
public class IndicatorPointServiceImpl implements IndicatorPointService {

	private IndicatorPointDao indicatorPointDao;

	@Resource(name = "indicatorPointDaoImpl")
	public void setIndicatorPointDao(IndicatorPointDao indicatorPointDao) {
		this.indicatorPointDao = indicatorPointDao;
	}

	private GraduateRequirementDao graduateRequirementDao;

	@Resource(name = "graduateRequirementDaoImpl")
	public void setGraduateRequirementDao(
			GraduateRequirementDao graduateRequirementDao) {
		this.graduateRequirementDao = graduateRequirementDao;
	}

	@Override
	public List<List<IndicatorPoint>> selectAllIndPoint() {
		List<GraduateRequirement> graduateRequirement = graduateRequirementDao
				.selectAllGraReq();
		List<List<IndicatorPoint>> indicatorPoints = new LinkedList<List<IndicatorPoint>>();

		for (int i = 0; i < graduateRequirement.size(); i++) {
			GraduateRequirement gr = (GraduateRequirement) graduateRequirement
					.get(i);
			List<IndicatorPoint> ips = indicatorPointDao
					.selectIndPointByParentId(gr.getGraReqId());
			indicatorPoints.add(ips);
		}

		return indicatorPoints;
	}

	@Override
	public IndicatorPoint selectIndPointById(Integer id) {

		return indicatorPointDao.selectIndPointById(id);
	}

}
