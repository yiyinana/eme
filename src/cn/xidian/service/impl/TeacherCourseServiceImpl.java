package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherCourseDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.TeacherCourse;
import cn.xidian.service.TeacherCourseService;

@Component("teacherCourseServiceImpl")
public class TeacherCourseServiceImpl implements TeacherCourseService {

	private TeacherCourseDao teacherCourseDao;

	@Resource(name = "teacherCourseDaoImpl")
	public void setTeacherCourseDao(TeacherCourseDao teacherCourseDao) {
		this.teacherCourseDao = teacherCourseDao;
	}

	@Override
	public List<Course> selectByNumAndTerm(String tchrNum) {
		List<Course> curs = null;
		List<TeacherCourse> tchrCursList = teacherCourseDao
				.findByNumAndTerm(tchrNum);
		//List<Integer> cursId = new LinkedList<Integer>();
		if (tchrCursList != null) {
			if (tchrCursList.size() > 0) {
				curs = new LinkedList<Course>();
				for (int i = 0; i < tchrCursList.size(); i++) {
					curs.add(tchrCursList.get(i).getCourse());
				}
			}
		}
		
		return curs;
	}

}
