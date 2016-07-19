package cn.xidian.service.impl;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.ClazzDao;
import cn.xidian.dao.CourseDao;
import cn.xidian.dao.StudentCourseDao;
import cn.xidian.dao.StudentDao;
import cn.xidian.dao.StudentItemDao;
import cn.xidian.dao.StudentPerformanceHandleDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.Course;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.StudentItem;
import cn.xidian.exception.ClazzNotExistException;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.StudentExistsException;
import cn.xidian.exception.StudentNotExistException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.AdminStudentService;
import cn.xidian.utils.ImportExecl;
import cn.xidian.utils.ServiceUtils;
import cn.xidian.web.bean.AdminStuLimits;

@Component
public class AdminStudentServiceImpl implements AdminStudentService {

	@Override
	public boolean addStudent(Student student) {
		Student studentTemp = studentDao.findBySchNum(student.getStuSchNum());
		if (studentTemp != null) {
			throw new StudentExistsException("该学生已存在，请勿重复添加");
		}
		String initTchrPwd = student.getStuSchNum();
		try {
			student.setStuPwd(ServiceUtils.md5(initTchrPwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return studentDao.addByAdmin(student);
	}

	@Override
	public boolean updateStudent(Student student) {
		Student studentTemp = studentDao.findBySchNum(student.getStuSchNum());
		studentTemp.setStuSchNum(student.getStuSchNum());
		String initTchrPwd = student.getStuSchNum();
		try {
			studentTemp.setStuPwd(ServiceUtils.md5(initTchrPwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		studentTemp.setStuName(student.getStuName());
		studentTemp.setStuSchNum(student.getStuSchNum());
		studentTemp.setStuGender(student.getStuGender());
		studentTemp.setStuNation(student.getStuNation());
		studentTemp.setStuAttendDate(student.getStuAttendDate());
		studentTemp.setClazz(student.getClazz());
		studentTemp.setStuSchLength(student.getStuSchLength());
		studentTemp.setStuIdentNum(student.getStuIdentNum());
		return studentDao.updateByAdmin(studentTemp);
	}

	@Override
	public Student selectStudentBySchNum(String schNum) {
		return studentDao.findBySchNum(schNum);
	}

	@Override
	public Set<Student> selectStudentByName(String name) {
		return studentDao.findByName(name);
	}

	@Override
	public boolean deleteStudent(String schNum) {
		Student studentTemp = studentDao.findBySchNum(schNum);
		if (studentTemp == null) {
			throw new StudentNotExistException("要删除的学生不存在");
		}
		studentDao.deleteByAdmin(studentTemp.getStuId());
		return true;
	}

	@Override
	public boolean selectStuBySchNum(String schNum) {
		studentDao.findBySchNum(schNum);
		return false;
	}

	private StudentDao studentDao;

	@Override
	@Resource(name = "studentDaoImpl")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	private StudentPerformanceHandleDao stuPerHanDao;

	@Resource(name = "studentPerformanceHandleDaoImpl")
	public void setStuPerHanDao(StudentPerformanceHandleDao stuPerHanDao) {
		this.stuPerHanDao = stuPerHanDao;
	}

	private ClazzDao clazzDao;

	@Resource(name = "clazzDaoImpl")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	private StudentItemDao studentItemDao;

	@Resource(name = "studentItemDaoImpl")
	public void setStudentItemDao(StudentItemDao studentItemDao) {
		this.studentItemDao = studentItemDao;

	}

	private StudentCourseDao studentCourseDao;

	@Resource(name = "studentCourseDaoImpl")
	public void setStudentCourseDao(StudentCourseDao studentCourseDao) {
		this.studentCourseDao = studentCourseDao;
	}

	@Override
	public List<Student> selectAllStus() {
		return studentDao.selectAllStus();
	}

	@Override
	public Set<Student> selectStuLimits(AdminStuLimits limits) {

		if (limits == null) {
			return null;
		}

		Set<Student> retStudents = new LinkedHashSet<Student>();
		retStudents.add(studentDao.findBySchNum(limits.getStuSchNum()));
		retStudents.addAll(studentDao.findByName(limits.getStuName()));
		retStudents.addAll(studentDao.findByClazz(limits.getStuClazz()));
		retStudents.remove(null);

		Set<Object> set = new LinkedHashSet<Object>();
		Student student;
		String stuSchNum = limits.getStuSchNum();
		if (!(stuSchNum == null || "".equals(stuSchNum))) {
			Iterator<Student> iterator1 = retStudents.iterator();
			while (iterator1.hasNext()) {
				student = iterator1.next();
				if (!(stuSchNum.equals(student.getStuSchNum()))) {
					set.add(student);
				}
			}
			retStudents.removeAll(set);
			set.clear();
		}
		String stuName = limits.getStuName();
		if (!(stuName == null || "".equals(stuName))) {
			Iterator<Student> iterator2 = retStudents.iterator();
			while (iterator2.hasNext()) {
				student = iterator2.next();
				if (!(stuName.equals(student.getStuName()))) {
					set.add(student);
				}
			}
			retStudents.removeAll(set);
			set.clear();
		}
		Integer clazzId = limits.getStuClazz();
		if (clazzId != null) {
			Iterator<Student> iterator3 = retStudents.iterator();
			while (iterator3.hasNext()) {
				student = iterator3.next();
				if (!(clazzId.equals(student.getClazz().getClaId()))) {
					set.add(student);
				}
			}
			retStudents.removeAll(set);
			set.clear();
		}

		retStudents.remove(null);
		System.out.println("测试："+retStudents.size());
		
		return retStudents;
	}

	ImportExecl importExcel = new ImportExecl();

	@Override
	public boolean addStuExcel(String path) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Student> stulist = new LinkedList<Student>();
		result = importExcel.read(path);
		String stuNum = "";
		String claName = "";
		String stuGender = "";
		String stuPwd = "";
		String stuIdentNum = "";
		Student student = null;
		Student studentTemp = null; // 用来判断是否存在
		Clazz clazz = null;
		List<String> existStu = new LinkedList<String>();// 用来记录需要更新的学生的学号
		List<String> notExistCla = new LinkedList<String>();
		List<String> errStuIdent = new LinkedList<String>();
		if (result.size() > 0) {
			for (int i = 1; i < result.size(); i++) {
				student = new Student();
				List<String> cellList = result.get(i);
				boolean flag = true;
				if (cellList.size() > 0) {
					for (int m = 0; m < cellList.size(); m++) {
						if (!cellList.get(m).equals("")) {
							flag = false;
						}
					}
				}
				if (flag) {
					continue;
				}
				stuNum = cellList.get(1).toString().trim();
				if (!stuNum.equals("")) {
					studentTemp = studentDao.findBySchNum(stuNum);
					if (studentTemp != null) {
						existStu.add(stuNum);
					}
					student.setStuSchNum(stuNum);
				}
				claName = cellList.get(4).toString();
				if (!claName.equals("")) {
					clazz = clazzDao.selectByName(claName);
					if (clazz != null) {
						student.setClazz(clazz);
					} else {
						notExistCla.add(claName);
					}
				}
				student.setStuName(cellList.get(0));
				stuGender = cellList.get(2);
				if (stuGender.equals("男")) {
					student.setStuGender(true);
				} else if (stuGender.equals("女")) {
					student.setStuGender(false);
				}
				student.setStuNation(cellList.get(3));
				stuIdentNum = cellList.get(5).toString();
				if (!(cellList.get(5).toString().equals(""))) {
					student.setStuIdentNum(cellList.get(5));
				}
//				if (stuIdentNum.length() != 18) {
//					errStuIdent.add(stuNum);
//				}
				student.setStuIdentNum(stuIdentNum);
				if (!(cellList.get(6).toString().equals(""))) {
					student.setStuSchLength(Integer.parseInt(cellList.get(6)));
				}
				if (!(cellList.get(7).toString().equals(""))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					try {
						date = sdf.parse(cellList.get(7).toString());
						student.setStuAttendDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				// 密码
				try {
					stuPwd = ServiceUtils.md5(stuNum);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				student.setStuPwd(stuPwd);
				student.setIsDelete(1);
				stulist.add(student);
			}
//			if (errStuIdent.size() > 0) {
//				String notExiString = "";
//				for (int j = 0; j < errStuIdent.size(); j++) {
//					notExiString += errStuIdent.get(j) + ",";
//				}
//				notExiString = notExiString.substring(0,
//						notExiString.length() - 1);
//				throw new StudentNotExistException("学号为" + notExiString
//						+ "的身份证号有误");
//			}
			if (notExistCla.size() > 0) {
				String notExiString = "";
				for (int j = 0; j < notExistCla.size(); j++) {
					notExiString += notExistCla.get(j) + ",";
				}
				notExiString = notExiString.substring(0,
						notExiString.length() - 1);
				throw new ClazzNotExistException("班级：" + notExiString + "不存在");
			}
		}
		if ((stulist.size() > 0) && (existStu.size() > 0)
				&& (errStuIdent.size() == 0)) { // 有重复的学生
			List<Student> stulistRep = new LinkedList<Student>();// 重复的学生
			for (int i = 0; i < existStu.size(); i++) { // 将重复的和未重复的分开
				String stuNumTemp = "";
				for (int j = 0; j < stulist.size(); j++) {
					stuNumTemp = stulist.get(j).getStuSchNum();
					if (stuNumTemp.equals(existStu.get(i))) {
						stulistRep.add(stulist.get(j));
					}
				}
			}
			stulist.removeAll(stulistRep);
			if (stulist.size() > 0) {// 未重复的
				for (int m = 0; m < stulist.size(); m++) {
					studentDao.addByAdmin(stulist.get(m));// add
				}
			}
			if (stulistRep.size() > 0) {// 重复的
				for (int m = 0; m < stulistRep.size(); m++) {
					studentDao.updateByStudent(stulistRep.get(m));// update
				}
			}
		} else if ((stulist.size() > 0) && (existStu.size() == 0)
				&& (errStuIdent.size() == 0)) { // 没有重复的学生
			for (int i = 0; i < stulist.size(); i++) {
				studentDao.addByAdmin(stulist.get(i));// add
			}
		}
		return true;
	}

	@Override
	public boolean addStuCursExcel(String path) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		result = importExcel.read(path);
		String stuNum = "";
		String cursNum = "";
		Student student = null;
		Course course = null;
		StudentCourse studentCourse = null;
		List<String> notExistStu = new LinkedList<String>();
		List<String> notExistCurs = new LinkedList<String>();
		if (result.size() > 0) {
			for (int i = 1; i < result.size(); i++) {
				studentCourse = new StudentCourse();
				List<String> cellList = result.get(i);
				boolean flag = true;
				if (cellList.size() > 0) {
					for (int m = 0; m < cellList.size(); m++) {
						if (!cellList.get(m).equals("")) {
							flag = false;
						}
					}
				}
				if (flag) {
					continue;
				}
				stuNum = cellList.get(0).toString();
				if (!stuNum.equals("")) {
					student = studentDao.findBySchNum(stuNum);
					if (student != null) {
						studentCourse.setStudent(student);
					} else {
						notExistStu.add(stuNum);
					}
				}
				cursNum = cellList.get(2);
				if (!cursNum.equals("")) {
					course = courseDao.findByNum(cursNum);
					if (course != null) {
						studentCourse.setCourse(course);
					} else {
						notExistCurs.add(cursNum);
					}
				}
				studentCourse.setStudent(student);
				studentCourse.setCourse(course);
				stuCurs.add(studentCourse);
			}
			if (notExistStu.size() > 0) {
				String notExiString = "";
				for (int j = 0; j < notExistStu.size(); j++) {
					notExiString += notExistStu.get(j) + ",";
				}
				notExiString = notExiString.substring(0,
						notExiString.length() - 1);
				throw new TeacherNotExistException("学号为" + notExiString
						+ "的学生不存在");
			}
			if (notExistCurs.size() > 0) {
				String notExiString = "";
				for (int j = 0; j < notExistCurs.size(); j++) {
					notExiString += notExistCurs.get(j) + ",";
				}
				notExiString = notExiString.substring(0,
						notExiString.length() - 1);
				throw new CourseNotExistException("课程编号为" + notExiString
						+ "的课程不存在");
			}
		}
		if ((stuCurs.size() > 0) && (notExistStu.size() == 0)
				&& (notExistCurs.size() == 0)) {
			Set<StudentCourse> set = new LinkedHashSet<StudentCourse>();
			studentCourse = null;
			List<StudentCourse> stuCursTemp = studentCourseDao.findAll();// 先获取数据库所有，然后去掉重复的
			Iterator<StudentCourse> iterator1 = stuCursTemp.iterator();
			Iterator<StudentCourse> iterator2 = null;// excel
			Integer cursId1 = null;
			Integer stuId1 = null;
			Integer cursId2 = null;
			Integer stuId2 = null;
			while (iterator1.hasNext()) {
				studentCourse = iterator1.next();
				cursId1 = studentCourse.getCourse().getCursId();
				stuId1 = studentCourse.getStudent().getStuId();
				iterator2 = stuCurs.iterator();
				while (iterator2.hasNext()) {
					studentCourse = iterator2.next();
					cursId2 = studentCourse.getCourse().getCursId();
					stuId2 = studentCourse.getStudent().getStuId();
					if (cursId1.equals(cursId2) && stuId1.equals(stuId2)) {
						set.add(studentCourse);
					}
				}
			}
			stuCurs.removeAll(set);
			set.clear();
			for (int i = 0; i < stuCurs.size(); i++) {
				stuPerHanDao.addStuCurs(stuCurs.get(i));
			}
		}
		return true;
	}

	@Override
	public List<StudentItem> selectStuItemsBySchNum(String schNum) {
		// TODO Auto-generated method stub
		return studentItemDao.selectByStuNum(schNum);
	}

	@Override
	public boolean judgeStuItem(StudentItem item) {
		// TODO Auto-generated method stub
		boolean judgeItem = studentItemDao.judgeStuItem(item);
		return judgeItem;
	}
}
