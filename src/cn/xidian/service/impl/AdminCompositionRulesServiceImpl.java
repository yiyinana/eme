package cn.xidian.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CompositionRulesDao;
import cn.xidian.dao.CourseDao;
import cn.xidian.entity.CompositionRules;
import cn.xidian.entity.Course;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.service.AdminCompositionRulesService;

@Component("adminCompositionRulesServiceImpl")
public class AdminCompositionRulesServiceImpl implements
		AdminCompositionRulesService {

	private CompositionRulesDao compositionRulesDao;

	@Resource(name = "compositionRulesDaoImpl")
	public void setCompositionRulesDao(CompositionRulesDao compositionRulesDao) {
		this.compositionRulesDao = compositionRulesDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}


	@Override
	public CompositionRules selectCompRulesByCursId(Integer cursId) {
		return compositionRulesDao.selectCompRulesByCursId(cursId);
	}

	@Override
	public boolean updateByCursId(CompositionRules crs, Integer cursId) {
		Course courseTemp = courseDao.findById(cursId);
		if (courseTemp == null) {
			throw new CourseNotExistException("要更新的课程不存在");
		}
		//不存在则添加，存在则直接更新
		crs.setCourse(courseTemp);
		CompositionRules rulse = compositionRulesDao.selectCompRulesByCursId(cursId);
		if(rulse == null)compositionRulesDao.addCompRules(crs);
		else compositionRulesDao.updateCompRules(crs);
		return true;
	}
}
