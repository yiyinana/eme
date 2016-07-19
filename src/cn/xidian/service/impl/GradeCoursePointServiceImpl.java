package cn.xidian.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.GradeCoursePointDao;
import cn.xidian.dao.MajorTargetValueDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.GradeCoursePoint;
import cn.xidian.entity.MajorTargetValue;
import cn.xidian.exception.GradeCoursePointNotExistException;
import cn.xidian.service.GradeCoursePointService;

@Component("gradeCoursePointServiceImpl")
public class GradeCoursePointServiceImpl implements GradeCoursePointService{

	private GradeCoursePointDao gradeCoursePointDao;

	@Resource(name = "gradeCoursePointDaoImpl")
	public void setGradeCoursePointDao(GradeCoursePointDao gradeCoursePointDao) {
		this.gradeCoursePointDao = gradeCoursePointDao;
	}
	
	private MajorTargetValueDao majorTargetValueDao;

	@Resource(name = "majorTargetValueDaoImpl")
	public void setMajorTargetValueDao(MajorTargetValueDao majorTargetValueDao) {
		this.majorTargetValueDao = majorTargetValueDao;
	}
	
	@Override
	public List<GradeCoursePoint> selectByPointIdAndGrade(Integer pointId,String grade) {
		return gradeCoursePointDao.selectByPointIdAndGrade(pointId,grade);
	}
	
	@Override
	public List<GradeCoursePoint> selectByCursIdAndGrade(Integer cursId,
			String grade) {
		return gradeCoursePointDao.selectByCursIdAndGrade(cursId,grade);
	}
	
	@Override
	public boolean updateCursPowerByGradeCursPointIdAndGrade(
			List<GradeCoursePoint> gradeCursPoints, String grade) {
		Double yearTargetValue = 0.0;//该指标点的学年达成度
		for(int i=0;i<gradeCursPoints.size();i++){
			gradeCoursePointDao.updateCursPowerByCursPointIdAndGrade(gradeCursPoints.get(i),grade);
			if(gradeCursPoints.get(i).getCursEvaValue() == null){
				gradeCursPoints.get(i).setCursEvaValue(0.0);
			}
			yearTargetValue += gradeCursPoints.get(i).getCursEvaValue()*gradeCursPoints.get(i).getCursPower();
		}
		
		//指标点学年达成度写入数据库
		MajorTargetValue mtv = new MajorTargetValue();
		mtv.setPoint(gradeCursPoints.get(0).getPoint());
		mtv.setGrade(grade);
		mtv.setValue(yearTargetValue);
		MajorTargetValue existMtv = majorTargetValueDao.selectByPointAndTerm(grade,gradeCursPoints.get(0).getPoint().getIndPointId());
		if(existMtv != null){
			majorTargetValueDao.delete(existMtv);
		}
		
		majorTargetValueDao.add(mtv);
		
		return true;
	}

	@Override
	public Set<Course> selectByGrade(String grade) {
		Set<Course> courses = new HashSet<Course>();
		List<GradeCoursePoint> gradeCursPoints = gradeCoursePointDao.selectByGrade(grade);
		if(gradeCursPoints == null) {
			throw new GradeCoursePointNotExistException("对不起，没有找到相关信息！");
		}
		for(int i = 0; i < gradeCursPoints.size(); i++) {
			courses.add(gradeCursPoints.get(i).getCourse());
		}
		return courses;
	}


}
