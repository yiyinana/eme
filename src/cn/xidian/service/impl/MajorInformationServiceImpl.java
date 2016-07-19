package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.MajorInformationDao;
import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.MajorInformation;
import cn.xidian.service.MajorInformationService;

@Component("majorInformationServiceImpl")
public class MajorInformationServiceImpl implements MajorInformationService{

	private MajorInformationDao majorInformationDao;
	@Resource(name="majorInformationDaoImpl")
	public void setMajorInformationDao(MajorInformationDao majorInformationDao) {
		this.majorInformationDao = majorInformationDao;
	}
	
	@Override
	public List<GraduateRequirement> selectGraReq() {
		return majorInformationDao.selectGraReq();
	}


	@Override
	public List<IndicatorPoint> selectPointByReqId(Integer reqId) {
		return majorInformationDao.selectPointByReqId(reqId);
	}

	@Override
	public boolean updateMajorInfo(MajorInformation majorInformation) {
		// TODO Auto-generated method stub
		boolean obj;
		MajorInformation majorInfoList = majorInformationDao.selectMajorInfo();
		if (majorInfoList == null) {
		  obj = majorInformationDao.addMajorInfo(majorInformation);
		} else {
		  obj = majorInformationDao.updateMajorInfo(majorInformation);
		}
		return obj;
	}

	@Override
	public MajorInformation selectMajorInfo() {
		// TODO Auto-generated method stub
		MajorInformation obj=majorInformationDao.selectMajorInfo();
		return obj;
	}

}
