package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.IndicatorPoint;

public interface IndicatorPointService {
	
	List<List<IndicatorPoint>> selectAllIndPoint();
	
	IndicatorPoint selectIndPointById(Integer id);
	
}
