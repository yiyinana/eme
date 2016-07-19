package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.ContributeTarget;

public interface ContributeTargetDao {

	List<ContributeTarget> selectByTarget(Integer ttId);
}
