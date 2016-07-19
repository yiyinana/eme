package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.MajorInformation;

public interface MajorInformationDao {

	List<GraduateRequirement> selectGraReq();
	
	List<IndicatorPoint> selectPointByReqId(Integer reqId);
MajorInformation selectMajorInfo();
	
	boolean addMajorInfo(MajorInformation majorInformation);
	
	boolean updateMajorInfo(MajorInformation majorInformation);
		
}
