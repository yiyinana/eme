package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.MajorTargetValueDao;
import cn.xidian.entity.MajorTargetValue;
import cn.xidian.service.MajorTargetValueService;

@Component("majorTargetValueServiceImpl")
public class MajorTargetValueServiceImpl implements MajorTargetValueService {

	private MajorTargetValueDao majorTargetValueDao;

	@Resource(name = "majorTargetValueDaoImpl")
	public void setMajorTargetValueDao(MajorTargetValueDao majorTargetValueDao) {
		this.majorTargetValueDao = majorTargetValueDao;
	}

	@Override
	public List<MajorTargetValue> selectByGrade(String grade) {
		List<MajorTargetValue> mtv = majorTargetValueDao.selectByGrade(grade);

		// 按indPointId排序
		for (int i = 0; i < mtv.size(); i++) {
			for (int j = i; j < mtv.size(); j++) {
				if (mtv.get(i).getPoint().getIndPointId() > mtv.get(j)
						.getPoint().getIndPointId()) {
					MajorTargetValue temp = mtv.get(i);
					mtv.set(i, mtv.get(j));
					mtv.set(j, temp);
				}
			}
		}

		return mtv;
	}

}
