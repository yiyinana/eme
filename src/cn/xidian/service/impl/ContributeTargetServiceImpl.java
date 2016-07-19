package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ContributeTargetDao;
import cn.xidian.entity.ContributeTarget;
import cn.xidian.service.ContributeTargetService;

@Component
public class ContributeTargetServiceImpl implements ContributeTargetService {

	private ContributeTargetDao contributeTargetDao;

	@Resource(name = "contributeTargetDaoImpl")
	public void setContributeTargetDao(ContributeTargetDao contributeTargetDao) {
		this.contributeTargetDao = contributeTargetDao;
	}

	@Override
	public List<ContributeTarget> selectByTarget(Integer ttId) {
		return contributeTargetDao.selectByTarget(ttId);
	}

}
