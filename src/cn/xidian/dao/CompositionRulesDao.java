package cn.xidian.dao;

import cn.xidian.entity.CompositionRules;

public interface CompositionRulesDao {

	boolean addCompRules(CompositionRules compositionRules);

	boolean updateCompRules(CompositionRules compositionRules);

	CompositionRules selectByCourse(String cursNum);
	
	CompositionRules selectCompRulesByCursId(Integer cursId);
	
}
