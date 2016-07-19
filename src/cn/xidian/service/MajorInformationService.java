package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.GraduateRequirement;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.MajorInformation;

public interface MajorInformationService {

	List<GraduateRequirement> selectGraReq();

	List<IndicatorPoint> selectPointByReqId(Integer reqId);

	boolean updateMajorInfo(MajorInformation majorInformation);

	MajorInformation selectMajorInfo();
}
