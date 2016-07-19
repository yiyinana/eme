package cn.xidian.service.impl;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import cn.xidian.dao.StudentCourseDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.StudentCourse;
import cn.xidian.service.StudentCourseService;
import cn.xidian.web.bean.StuCursLimits;

@Component("studentCourseServiceImpl")
public class StudentCourseServiceImpl implements StudentCourseService {

	private StudentCourseDao studentCourseDao;

	@Resource(name = "studentCourseDaoImpl")
	public void setStudentCourseDao(StudentCourseDao studentCourseDao) {
		this.studentCourseDao = studentCourseDao;
	}

	@Override
	public List<Course> selectByNum(String stuNum) {
		List<Course> curs = null;
		List<StudentCourse> stuCursList = studentCourseDao.findByNum(stuNum);
		//List<Integer> cursId = new LinkedList<Integer>();
		if (stuCursList != null) {
			if (stuCursList.size() > 0) {
				curs = new LinkedList<Course>();
				for (int i = 0; i < stuCursList.size(); i++) {
					curs.add(stuCursList.get(i).getCourse());
				}
			}
		}
		
		return curs;
	}

	@Override
	public List<StudentCourse> selectByNumTerm(String stuNum, String currTerm) {
		List<StudentCourse> stuCursList = new LinkedList<StudentCourse>();
		if (currTerm.equals("")) {
			stuCursList = studentCourseDao.findByNum(stuNum);
		} else {
			stuCursList = studentCourseDao.findByNumAndTerm(stuNum, currTerm);
		}
		return stuCursList;
	}

	@Override
	public Set<StudentCourse> selectStuCursLimits(StuCursLimits limits) {
		if (limits == null) {
			return null;
		}
		// 根据curName得到cursId,根据工号得到cursId,两者交集得到cursId;再根据claName得到stuId;
		// 最后由cursId和stuId得到Set<StudentCourse>
		Set<StudentCourse> retStudents = new LinkedHashSet<StudentCourse>();
		List<StudentCourse> sclist = new LinkedList<StudentCourse>();
		if (limits.getClaName().toString().equals("")) {// 全部班级
			sclist = studentCourseDao.findByLimitAllClazz(limits.getCursName(),
					limits.getTchrSchNum());
		} else {
			sclist = studentCourseDao.findByLimit(limits.getClaName(),
					limits.getCursName(), limits.getTchrSchNum());
		}
		retStudents.addAll(sclist);

		retStudents.remove(null);
		return retStudents;
	}
}
