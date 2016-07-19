package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.AdminCourseContributeTargetValueDao;
import cn.xidian.dao.CoursePointDao;
import cn.xidian.dao.TeachingTargetDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.CoursePoint;
import cn.xidian.entity.IndicatorPoint;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.service.CoursePointService;

@Component
public class CoursePointServiceImpl implements CoursePointService{
	
	private CoursePointDao coursePointDao;

	@Resource(name = "coursePointDaoImpl")
	public void setCoursePointDao(CoursePointDao coursePointDao) {
		this.coursePointDao = coursePointDao;
	}
	
	private TeachingTargetDao teachingTargetDao;

	@Resource(name = "teachingTargetDaoImpl")
	public void setTeachingTargetDao(TeachingTargetDao teachingTargetDao) {
		this.teachingTargetDao = teachingTargetDao;
	}
	
	private AdminCourseContributeTargetValueDao adminCourseContributeTargetValueDao;

	@Resource(name = "adminCourseContributeTargetValueDaoImpl")
	public void setAdminCourseContributeTargetValueDao(
			AdminCourseContributeTargetValueDao adminCourseContributeTargetValueDao) {
		this.adminCourseContributeTargetValueDao = adminCourseContributeTargetValueDao;
	}
	
	@Override
	public boolean modifyByCursId(List<IndicatorPoint> points, Integer cursId) {
		//先删除后添加
		List<TeachingTarget> targets = teachingTargetDao.selectByCursId(cursId);
		for(int i=0;i<targets.size();i++){
			adminCourseContributeTargetValueDao.deleteByTargetId(targets.get(i).getTchTargetId());
		}
		List<CoursePoint> existPoints = coursePointDao.selectByCursId(cursId);
		if(existPoints.size() != 0){
			for(int i=0;i<existPoints.size();i++){
				coursePointDao.delete(existPoints.get(i));
			}
		}
		Course course = new Course();
		course.setCursId(cursId);
		for(int i=0;i<points.size();i++){
			CoursePoint cPoint = new CoursePoint();
			cPoint.setIndPoint(points.get(i));
			cPoint.setCourse(course);
			coursePointDao.add(cPoint);
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<IndicatorPoint> selectPointByCursId(Integer cursId) {
		List<CoursePoint> cPoints = coursePointDao.selectByCursId(cursId);
		List<IndicatorPoint> points = new LinkedList<IndicatorPoint>();
		for(int i=0;i<cPoints.size();i++){
			points.add(cPoints.get(i).getIndPoint());
		}
		return points;
	}

	@Override
	public List<CoursePoint> selectByCursId(Integer cursId) {
		// TODO Auto-generated method stub
		return coursePointDao.selectByCursId(cursId);
	}

	@Override
	public List<CoursePoint> selectByPointId(Integer pointId) {
		return coursePointDao.selectByPointId(pointId);
	}

}
