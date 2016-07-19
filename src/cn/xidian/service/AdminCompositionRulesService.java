package cn.xidian.service;

import cn.xidian.entity.CompositionRules;

public interface AdminCompositionRulesService {
		
	CompositionRules selectCompRulesByCursId(Integer cursId);
	
	boolean updateByCursId(CompositionRules crs, Integer cursId);
}
