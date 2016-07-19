package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.IndicatorPoint;

public interface IndicatorPointDao {
	
	List<IndicatorPoint> selectIndPointByParentId(Integer id);
	
	IndicatorPoint selectIndPointById(Integer id);
		
}
