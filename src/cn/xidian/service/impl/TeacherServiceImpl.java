package cn.xidian.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzCoursePointDao;
import cn.xidian.dao.ClazzDao;
import cn.xidian.dao.CompositionRulesDao;
import cn.xidian.dao.ContributeTargetDao;
import cn.xidian.dao.CourseDao;
import cn.xidian.dao.IsEvaluateDao;
import cn.xidian.dao.StudentCourseDao;
import cn.xidian.dao.StudentDao;
import cn.xidian.dao.TeacherCourseDao;
import cn.xidian.dao.TeacherDao;
import cn.xidian.dao.TeachingTargetDao;
import cn.xidian.dao.TeachingTargetEvaluateDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.ClazzCoursePoint;
import cn.xidian.entity.CompositionRules;
import cn.xidian.entity.ContributeTarget;
import cn.xidian.entity.Course;
import cn.xidian.entity.IsEvaluate;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherCourse;
import cn.xidian.entity.TeachingTarget;
import cn.xidian.entity.TeachingTargetEvaluate;
import cn.xidian.exception.ClazzNotExistException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.CursRulesNotExistException;
import cn.xidian.exception.StudentCourseNotExistsException;
import cn.xidian.exception.TchingTargetNotExistException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.TeacherService;
import cn.xidian.utils.ServiceUtils;

@Component
public class TeacherServiceImpl implements TeacherService {

	private TeacherCourseDao teacherCourseDao;

	@Resource(name = "teacherCourseDaoImpl")
	public void setTeacherCourseDao(TeacherCourseDao teacherCourseDao) {
		this.teacherCourseDao = teacherCourseDao;
	}

	private StudentDao studentDao;

	@Resource(name = "studentDaoImpl")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	private StudentCourseDao studentCourseDao;

	@Resource(name = "studentCourseDaoImpl")
	public void setStudentCourseDao(StudentCourseDao studentCourseDao) {
		this.studentCourseDao = studentCourseDao;
	}

	private CompositionRulesDao compositionRulesDao;

	@Resource(name = "compositionRulesDaoImpl")
	public void setCompositionRulesDao(CompositionRulesDao compositionRulesDao) {
		this.compositionRulesDao = compositionRulesDao;
	}

	private TeachingTargetDao teachingTargetDao;

	@Resource(name = "teachingTargetDaoImpl")
	public void setTeachingTargetDao(TeachingTargetDao teachingTargetDao) {
		this.teachingTargetDao = teachingTargetDao;
	}

	private TeachingTargetEvaluateDao teachingTargetEvaluateDao;

	@Resource(name = "teachingTargetEvaluateDaoImpl")
	public void setTeachingTargetEvaluateDao(
			TeachingTargetEvaluateDao teachingTargetEvaluateDao) {
		this.teachingTargetEvaluateDao = teachingTargetEvaluateDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	private ClazzCoursePointDao clazzCoursePointDao;

	@Resource(name = "clazzCoursePointDaoImpl")
	public void setClazzCoursePointDao(ClazzCoursePointDao clazzCoursePointDao) {
		this.clazzCoursePointDao = clazzCoursePointDao;
	}

	private ClazzDao clazzDao;

	@Resource(name = "clazzDaoImpl")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	private ContributeTargetDao contributeTargetDao;

	@Resource(name = "contributeTargetDaoImpl")
	public void setContributeTargetDao(ContributeTargetDao contributeTargetDao) {
		this.contributeTargetDao = contributeTargetDao;
	}

	private IsEvaluateDao isevaluateDao;

	@Resource(name = "isEvaluateDaoImpl")
	public void setIsevaluateDao(IsEvaluateDao isevaluateDao) {
		this.isevaluateDao = isevaluateDao;
	}

	private TeacherDao teacherDao;

	@Resource(name = "teacherDaoImpl")
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public boolean loginValidate(String tchrSchNum, String tchrPwd) {
		try {
			return teacherDao.findBySchNumAndPwd(tchrSchNum,
					ServiceUtils.md5(tchrPwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Teacher selectInfBySchNum(String tchrSchNum) {
		return teacherDao.findBySchNum(tchrSchNum);
	}

	@Override
	public boolean updateInfBySchNum(Teacher teacher) {
		return teacherDao.updateByTeacher(teacher);
	}

	@Override
	public boolean modifyPassword(String tchrSchNum, String tchrPwd) {
		String passwordString = "";
		try {
			passwordString = ServiceUtils.md5(tchrPwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return teacherDao.modifyPassword(tchrSchNum, passwordString);
	}

	@Override
	public List<Course> selectTchCoursesByTchrNum(String tchrNum) {
		List<Course> courses = new LinkedList<Course>();
		List<TeacherCourse> tCourses = teacherCourseDao
				.findByNumAndTerm(tchrNum);
		for (int i = 0; i < tCourses.size(); i++) {
			courses.add(tCourses.get(i).getCourse());
		}
		return courses;
	}

	@Override
	public List<Course> selectChargeCoursesByTchrNum(String tchrNum) {
		return courseDao.selectTchChargeCursByTchrNum(tchrNum);
	}

	@Override
	public boolean caculateClazzTarget(String cursName, String claName,
			String tchrSchNum) {// 计算某班级某课程达成度
		// DecimalFormat df = new DecimalFormat("#.00");//
		// 用于格式化Double类型数据，保留两位小数

		// 找到班级和课程
		Course course = courseDao.findByNameAndTerm(cursName);
		if (course == null) {
			throw new CourseNotExistException("对不起，课程不存在！");
		}
		Clazz clazz = clazzDao.selectByName(claName);
		if (clazz == null) {
			throw new ClazzNotExistException("对不起，班级不存在！");
		}

		// 找到该课程的评分规则
		CompositionRules rules = compositionRulesDao.selectByCourse(course
				.getCursNum());
		if (rules == null) {
			throw new CursRulesNotExistException("未找到课程评分规则，请检查课程信息！");
		}

		Double midEvaPer = rules.getMidTermPer() / 100;// 期中成绩百分比
		Double finEvaPer = rules.getFinalExamPer() / 100;// 期末成绩百分比
		Double classEvaPer = rules.getClazzPer() / 100;// 课堂表现百分比
		Double workEvaPer = rules.getHomeworkResultPer() / 100;// 平时作业百分比
		Double expEvaPer = rules.getExpResultPer() / 100;// 实验成绩百分比

		Double classMidValueTotal = 0.0;
		Double classFinValueTotal = 0.0;
		Double classClazzValueTotal = 0.0;
		Double classWorkValueTotal = 0.0;
		Double classExpValueTotal = 0.0;

		Set<Student> students = studentDao.findByClazz(clazz.getClaId());
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();

		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			StudentCourse sc = new StudentCourse();
			Integer stuId = it.next().getStuId();
			sc = studentCourseDao.selectByCursIdAndStuId(course.getCursId(),
					stuId);
			if (sc != null) {
				stuCurs.add(sc);
			}
		}
		
		if(stuCurs.size()==0){
			throw new StudentCourseNotExistsException("请先上传成绩！");
		}

		// 计算班级总综合成绩
		for (int i = 0; i < stuCurs.size(); i++) {
			classMidValueTotal += stuCurs.get(i).getMidEvaValue();
			classFinValueTotal += stuCurs.get(i).getFinEvaValue();
			classClazzValueTotal += stuCurs.get(i).getClassEvaValue();
			classWorkValueTotal += stuCurs.get(i).getWorkEvaValue();
			classExpValueTotal += stuCurs.get(i).getExpEvaValue();
		}

		// 班级各分项值为各分项的平均值乘以所占百分比
		Double classMidValue = classMidValueTotal / stuCurs.size() * midEvaPer;// 班级期中成绩
		Double classFinValue = classFinValueTotal / stuCurs.size() * finEvaPer;// 班级期末成绩
		Double classClazzValue = classClazzValueTotal / stuCurs.size()
				* classEvaPer;// 班级课堂成绩
		Double classWorkValue = classWorkValueTotal / stuCurs.size()
				* workEvaPer;// 班级作业成绩
		Double classExpValue = classExpValueTotal / stuCurs.size() * expEvaPer;// 班级实验成绩

		List<TeachingTarget> tts = teachingTargetDao.selectByCursId(course
				.getCursId());
		if (tts.size() == 0) {
			throw new TchingTargetNotExistException("未找到课程目标，请检查课程信息！");
		}
		Double classMidTargetValue = 0.0;// 班级期中成绩目标值
		Double classFinTargetValue = 0.0;// 班级期末成绩目标值
		Double classClazzTargetValue = 0.0;// 班级课堂成绩目标值
		Double classWorkTargetValue = 0.0;// 班级作业成绩目标值
		Double classExpTargetValue = 0.0;// 班级实验成绩目标值

		for (int i = 0; i < tts.size(); i++) {
			classMidTargetValue += tts.get(i).getTchtargetMidTargetValue();
			classFinTargetValue += tts.get(i).getTchtargetFinTargetValue();
			classClazzTargetValue += tts.get(i).getTchtargetClassTargetValue();
			classWorkTargetValue += tts.get(i)
					.getTchtargetHomeworkTargetValue();
			classExpTargetValue += tts.get(i).getTchtargetExpTargetValue();
		}

		List<Double> b1s = new LinkedList<Double>();
		for (int i = 0; i < tts.size(); i++) {
			TeachingTargetEvaluate ttEvaluate = new TeachingTargetEvaluate();
			Double classMidEvaValue = 0.0;// 班级期中成绩评价值
			Double classFinEvaValue = 0.0;// 班级期末成绩评价值
			Double classClazzEvaValue = 0.0;// 班级课堂成绩评价值
			Double classWorkEvaValue = 0.0;// 班级作业成绩评价值
			Double classExpEvaValue = 0.0;// 班级实验成绩评价值
			Double a1 = 0.0;
			Double b1 = 0.0;
			// 先分别计算每一行的值，再把每一行加到列表中

			if (classMidTargetValue == 0.0) {
				classMidEvaValue = 0.0;
			} else {
				classMidEvaValue = classMidValue
						* tts.get(i).getTchtargetMidTargetValue()
						/ classMidTargetValue;
			}
			ttEvaluate.setTchtargetMidEvaValue(classMidEvaValue);
			if (classFinTargetValue == 0.0) {
				classFinEvaValue = 0.0;
			} else {
				classFinEvaValue = classFinValue
						* tts.get(i).getTchtargetFinTargetValue()
						/ classFinTargetValue;
			}
			ttEvaluate.setTchtargetFinEvaValue(classFinEvaValue);
			if (classClazzTargetValue == 0.0) {
				classClazzEvaValue = 0.0;
			} else {
				classClazzEvaValue = classClazzValue
						* tts.get(i).getTchtargetClassTargetValue()
						/ classClazzTargetValue;
			}
			ttEvaluate.setTchtargetClassEvaValue(classClazzEvaValue);
			if (classWorkTargetValue == 0.0) {
				classWorkEvaValue = 0.0;
			} else {
				classWorkEvaValue = classWorkValue
						* tts.get(i).getTchtargetHomeworkTargetValue()
						/ classWorkTargetValue;
			}
			ttEvaluate.setTchtargetWorkEvaValue(classWorkEvaValue);
			if (classExpTargetValue == 0.0) {
				classExpEvaValue = 0.0;
			} else {
				classExpEvaValue = classExpValue
						* tts.get(i).getTchtargetExpTargetValue()
						/ classExpTargetValue;
			}
			ttEvaluate.setTchtargetExpEvaValue(classExpEvaValue);

			a1 = classMidEvaValue + classFinEvaValue + classClazzEvaValue
					+ classWorkEvaValue + classExpEvaValue;
			double denominator = (tts.get(i).getTchtargetMidTargetValue()
					+ tts.get(i).getTchtargetFinTargetValue()
					+ tts.get(i).getTchtargetClassTargetValue()
					+ tts.get(i).getTchtargetHomeworkTargetValue() + tts.get(i)
					.getTchtargetExpTargetValue());
			if (denominator == 0) {
				b1 = 0.0;
			} else {
				b1 = a1 / denominator;
			}
			ttEvaluate.setA1(a1);
			ttEvaluate.setB1(b1);
			b1s.add(b1);// 把b1存到一个list，一会计算a2、b2拿出来用
			ttEvaluate.setClazz(clazz);
			ttEvaluate.setTeachingTarget(tts.get(i));
			TeachingTargetEvaluate tte = teachingTargetEvaluateDao
					.selectByClazzIdAndTargetId(clazz.getClaId(), tts.get(i)
							.getTchTargetId());
			// 存在则更新，不存在则添加
			if (tte == null) {
				teachingTargetEvaluateDao.addTchingTargetEvaValue(ttEvaluate);// 写入数据库
			} else {
				teachingTargetEvaluateDao
						.updateTchingTargetEvaValue(ttEvaluate);
			}
		}

		// 计算a2、b2
		/* 若已存在，则先删除。。。。。。。。。。。。待改进 */
		List<ClazzCoursePoint> existCursPoints = clazzCoursePointDao
				.selectByCursAndClazzId(course.getCursId(), clazz.getClaId());
		if (existCursPoints.size() != 0) {
			for (int i = 0; i < existCursPoints.size(); i++) {
				clazzCoursePointDao.deleteById(existCursPoints.get(i));
			}
		}

		// 然后计算并写入
		List<ContributeTarget> cts = contributeTargetDao.selectByTarget(tts
				.get(0).getTchTargetId());
		int n = cts.size();
		if (n == 0) {
			throw new CursRulesNotExistException("评分规则不完整，请检查课程信息！");
		}
		Double[] a2 = new Double[n];
		Double[] b2 = new Double[n];
		Double[] targetTarValue = new Double[n];
		for (int i = 0; i < n; i++) {
			a2[i] = 0.0;
			b2[i] = 0.0;
			targetTarValue[i] = 0.0;
		}
		List<ContributeTarget> ct = new LinkedList<ContributeTarget>();
		for (int j = 0; j < tts.size(); j++) {
			ct = contributeTargetDao
					.selectByTarget(tts.get(j).getTchTargetId());
			for (int i = 0; i < ct.size(); i++) {
				Double ai = ct.get(i).getConTarValue() * b1s.get(j);
				targetTarValue[i] += ct.get(i).getConTarValue();
				a2[i] += ai;
			}
		}

		for (int i = 0; i < n; i++) {
			ClazzCoursePoint ccp = new ClazzCoursePoint();
			if (targetTarValue[i] == 0) {
				b2[i] = 0.0;
			} else {
				b2[i] = a2[i] / targetTarValue[i];
			}
			ccp.setTargetTarValue(targetTarValue[i]);
			ccp.setA2(a2[i]);
			ccp.setB2(b2[i]);
			ccp.setClazz(clazz);
			ccp.setCourse(course);
			ccp.setIndPoint(cts.get(i).getIndicatorPoint());
			clazzCoursePointDao.add(ccp);
		}

		// 写入isevaluate表，有则update,无则insert
		Integer cursId = course.getCursId();
		Integer claId = clazz.getClaId();
		Teacher tchrTemp = teacherDao.findBySchNum(tchrSchNum);
		if (tchrTemp == null) {
			throw new TeacherNotExistException("工号为" + tchrSchNum + "的老师不存在！");
		}
		IsEvaluate isevalTemp = isevaluateDao.findByCursAndClazz(cursId, claId);
		if (isevalTemp != null) {
			isevalTemp.setTeacher(tchrTemp);
			isevalTemp.setEvaDate(new Date());
			isevaluateDao.updateIsevaluate(isevalTemp);
		} else {
			isevalTemp = new IsEvaluate();
			isevalTemp.setCourse(course);
			isevalTemp.setClazz(clazz);
			isevalTemp.setTeacher(tchrTemp);
			isevalTemp.setEvaDate(new Date());
			isevaluateDao.addIsevaluate(isevalTemp);
		}

		return true;
	}

}
