package cn.xidian.dao;

import java.util.List;



import cn.xidian.entity.IsEvaluate;

public interface IsEvaluateDao {

	IsEvaluate findByCursAndClazz(Integer cursId, Integer claId);
	
	List<IsEvaluate> selectByCursIdAndGrade(Integer cursId,String grade);
	
	List<IsEvaluate> findByCursId(Integer cursId);

	boolean updateIsevaluate(IsEvaluate isevaluate);

	boolean addIsevaluate(IsEvaluate isevaluate);

}
