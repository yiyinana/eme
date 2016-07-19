package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.AdminCourseContributeTargetValueDao;
import cn.xidian.dao.TeachingTargetDao;
import cn.xidian.entity.ContributeTarget;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.service.AdminCourseContributeTargetValueService;

@Component
public class AdminCourseContributeTargetValueServiceImpl implements
		AdminCourseContributeTargetValueService {

	private AdminCourseContributeTargetValueDao adminCourseContributeTargetValueDao;

	@Resource(name = "adminCourseContributeTargetValueDaoImpl")
	public void setAdminCourseContributeTargetValueDao(
			AdminCourseContributeTargetValueDao adminCourseContributeTargetValueDao) {
		this.adminCourseContributeTargetValueDao = adminCourseContributeTargetValueDao;
	}

	private TeachingTargetDao teachingTargetDao;

	@Resource(name = "teachingTargetDaoImpl")
	public void setTeachingTargetDao(TeachingTargetDao teachingTargetDao) {
		this.teachingTargetDao = teachingTargetDao;
	}

	@Override
	public boolean saveContributeTargetValue(
			List<ContributeTarget> contributeTargets) {
		for (int i = 0; i < contributeTargets.size(); i++) {
			adminCourseContributeTargetValueDao
					.saveContributeTargetValue(contributeTargets.get(i));
		}
		return true;
	}

	@Override
	public List<ContributeTarget> selectByCursId(Integer cursId) {
		List<ContributeTarget> contributeTargets = new LinkedList<ContributeTarget>();
		List<TeachingTarget> targets = teachingTargetDao.selectByCursId(cursId);
		for (int i = 0; i < targets.size(); i++) {
			List<ContributeTarget> target = adminCourseContributeTargetValueDao.selectByTargetId(targets.get(i).getTchTargetId());
			contributeTargets.addAll(target);
		}
		return contributeTargets;
	}

	@Override
	public boolean updateContributeTargetValue(
			List<ContributeTarget> contributeTargets,Integer cursId) {
		for (int i = 0; i < contributeTargets.size(); i++) {
			adminCourseContributeTargetValueDao
					.saveContributeTargetValue(contributeTargets.get(i));
		}
		return true;
	}

}
