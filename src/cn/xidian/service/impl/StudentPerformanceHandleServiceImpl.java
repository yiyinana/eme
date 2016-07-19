package cn.xidian.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzDao;
import cn.xidian.dao.CompositionRulesDao;
import cn.xidian.dao.CourseDao;
import cn.xidian.dao.StudentCourseDao;
import cn.xidian.dao.StudentDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.CompositionRules;
import cn.xidian.entity.Course;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.exception.ClazzNotExistException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.CursOrTermNotMatchException;
import cn.xidian.exception.CursRulesNotExistException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.service.StudentPerformanceHandleService;
import cn.xidian.utils.ImportExecl;

@Component
public class StudentPerformanceHandleServiceImpl implements
		StudentPerformanceHandleService {

	ImportExecl importExecl = new ImportExecl();

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

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	private ClazzDao clazzDao;

	@Resource(name = "clazzDaoImpl")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	@Override
	public boolean handleExcel(String path) {
		DecimalFormat df = new DecimalFormat("#.00");// 用于格式化Double类型数据，保留两位小数
		List<List<String>> result = new LinkedList<List<String>>();
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();

		result = importExecl.read(path);
		String stuNum = "";
		String cursName = "";
		String cursCurrTerm = "";
		String cursClazz = "";
		Student student = null;
		Course course = null;
		List<Clazz> clazzs = new LinkedList<Clazz>();
		// 从路径中截取文件名，再截取课程学期及课程名，班级，找到课程对象、班级对象，为空则抛异常
		String fileName = path.substring(path.lastIndexOf("/") + 1,
				path.length());// 截取文件名
		cursCurrTerm = fileName.substring(0, fileName.indexOf("_"));// 截取课程学期
		cursName = fileName.substring(fileName.indexOf("_") + 1,
				fileName.lastIndexOf("_"));// 截取课程名
		cursClazz = fileName.substring(fileName.lastIndexOf("_") + 1,
				fileName.lastIndexOf("."));
		String[] clazzArray = null;
		clazzArray = cursClazz.split(",");
		if ((!cursName.trim().equals("")) && (!cursCurrTerm.trim().equals(""))
				&& (!cursClazz.equals(""))) {
			course = courseDao.findByNameAndTerm(cursName);
			for (int i = 0; i < clazzArray.length; i++) {
				Clazz clazz = null;
				clazz = clazzDao.selectByName(clazzArray[i].trim());
				clazzs.add(clazz);
			}
			if (course == null) {
				throw new CourseNotExistException("课程不存在");
			}
			if (clazzs.size() == 0) {
				throw new ClazzNotExistException("班级不存在");
			}
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

		if (result != null) {
			List<String> firstLine = new LinkedList<String>();// 第一行公共信息（包括学期、班级、课程）
			firstLine = result.get(0);
			for(int i=0;i<clazzs.size();i++){
				String clazzName = clazzs.get(i).getClaName();
				boolean isContain = firstLine.get(3).contains(clazzName);
				if(!isContain){
					throw new CursOrTermNotMatchException("班级选择错误");
				}
			}
			if (!firstLine.get(5).equals(course.getCursName())) {
				throw new CursOrTermNotMatchException("课程选择错误");
			}
			// List<String> notExistStudent = new LinkedList<String>();//
			// 表格中有而数据库中不存在的学生
			for (int i = 2; i < result.size(); i++) { // i从2开始意味着去掉表头和公共信息
				StudentCourse studentCourse = new StudentCourse();
				List<String> cellList = result.get(i);
				// 判断有无该学生，有则获取学生id
				stuNum = cellList.get(0).toString();
				if (!stuNum.equals("")) {
					student = studentDao.findBySchNum(stuNum);
					if (student != null) {
						studentCourse.setStudent(student);
					} else {
						throw new StudentNotExistException("学号为" + stuNum
								+ "的学生不存在");
					}

					studentCourse.setCourse(course);

					StudentCourse sc = studentCourseDao.selectByCursIdAndStuId(
							course.getCursId(), student.getStuId());
					if (sc != null) {
						studentCourseDao.delete(sc);
					}

					String midString = cellList.get(2);
					String finString = cellList.get(3);
					String classString = cellList.get(4);
					String workString = cellList.get(5);
					String expString = cellList.get(6);
					// 若为空，则置为0
					if (midString.trim().length() == 0) {
						midString = "0";
					}
					if (finString.trim().length() == 0) {
						finString = "0";
					}
					if (classString.trim().length() == 0) {
						classString = "0";
					}
					if (workString.trim().length() == 0) {
						workString = "0";
					}
					if (expString.trim().length() == 0) {
						expString = "0";
					}

					Double midEvaValue = Double.parseDouble(midString);// 期中成绩
					Double finEvaValue = Double.parseDouble(finString);// 期末成绩
					Double classEvaValue = Double.parseDouble(classString);// 课堂表现
					Double workEvaValue = Double.parseDouble(workString);// 平时作业成绩
					Double expEvaValue = Double.parseDouble(expString);// 实验成绩

					// 计算单个学生的综合成绩
					Double evaValue = midEvaValue * midEvaPer + finEvaValue
							* finEvaPer + classEvaValue * classEvaPer
							+ workEvaValue * workEvaPer + expEvaValue
							* expEvaPer;

					evaValue = Double.valueOf(df.format(evaValue));

					studentCourse.setMidEvaValue(midEvaValue);
					studentCourse.setFinEvaValue(finEvaValue);
					studentCourse.setClassEvaValue(classEvaValue);
					studentCourse.setWorkEvaValue(workEvaValue);
					studentCourse.setExpEvaValue(expEvaValue);
					studentCourse.setEvaValue(evaValue);
					stuCurs.add(studentCourse);
				}
				else continue;
			}

		}
		else{
			throw new CursOrTermNotMatchException("未找到相应成绩");
		}

		if (stuCurs != null) {
			for (int i = 0; i < stuCurs.size(); i++) {
				// 单个学生成绩写入数据库
				studentCourseDao.add(stuCurs.get(i));
			}
		}

		return true;
	}

}
