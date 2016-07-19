package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.ContributeTarget;

public interface ContributeTargetService {

	List<ContributeTarget> selectByTarget(Integer ttId);
}
