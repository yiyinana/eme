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

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.TeacherCourseDao;
import cn.xidian.dao.TeacherDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherCourse;
import cn.xidian.exception.CourseNotExistException;
import cn.xidian.exception.TeacherExistsException;
import cn.xidian.exception.TeacherNotExistException;
import cn.xidian.service.AdminTeacherService;
import cn.xidian.utils.ImportExecl;
import cn.xidian.utils.ServiceUtils;
import cn.xidian.web.bean.AdminTchrLimits;

@Component
public class AdminTeacherServiceImpl implements AdminTeacherService {

	@Override
	public boolean addTeacher(Teacher teacher) {
		Teacher teacherTemp = teacherDao.findBySchNum(teacher.getTchrSchNum());
		if (teacherTemp != null) {
			throw new TeacherExistsException("该老师已存在，请勿重复添加");
		}
		String initTchrPwd = teacher.getTchrSchNum();
		try {
			teacher.setTchrPwd(ServiceUtils.md5(initTchrPwd));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return teacherDao.addByAdmin(teacher);
	}

	@Override
	public boolean deleteTeacher(String schNum) {
		Teacher teacherTemp = teacherDao.findBySchNum(schNum);
		if (teacherTemp == null) {
			throw new TeacherNotExistException("要删除的老师不存在");
		}
		teacherDao.deleteByAdmin(teacherTemp.getTchrId());
		return true;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		Teacher teacherTemp = teacherDao.findBySchNum(teacher.getTchrSchNum());
		if (teacherTemp == null) {
			throw new TeacherNotExistException("要更新的老师不存在");
		}
		teacherTemp.setTchrSchNum(teacher.getTchrSchNum());
		teacherTemp.setTchrName(teacher.getTchrName());
		teacherTemp.setTchrGender(teacher.getTchrGender());
		teacherTemp.setTchrNation(teacher.getTchrNation());
		teacherTemp.setTchrIdentNum(teacher.getTchrIdentNum());
		teacherTemp.setTchrTitle(teacher.getTchrTitle());
		teacherTemp.setTchrPhone(teacher.getTchrPhone());
		teacherTemp.setTchrEmail(teacher.getTchrEmail());
		teacherTemp.setTchrOfficeAddr(teacher.getTchrOfficeAddr());
		return teacherDao.updateByAdmin(teacherTemp);
	}

	@Override
	public Set<Teacher> selectUnderLimits(AdminTchrLimits limits) {

		if (limits == null) {
			return null;
		}

		Set<Teacher> retTeachers = new LinkedHashSet<Teacher>();
		retTeachers.add(teacherDao.findBySchNum(limits.getTchrSchNum()));
		retTeachers.addAll(teacherDao.findByName(limits.getTchrName()));
		retTeachers.addAll(teacherDao.findByTitle(limits.getTchrTitle()));
		retTeachers.remove(null);

		Set<Object> set = new LinkedHashSet<Object>();
		Teacher tempTeacher;
		String schNum = limits.getTchrSchNum();
		if (!(schNum == null || "".equals(schNum))) {
			Iterator<Teacher> iterator1 = retTeachers.iterator();
			while (iterator1.hasNext()) {
				tempTeacher = iterator1.next();
				if (!(limits.getTchrSchNum()
						.equals(tempTeacher.getTchrSchNum()))) {
					set.add(tempTeacher);
				}
			}
			retTeachers.removeAll(set);
			set.clear();
		}
		String name = limits.getTchrName();
		if (!(name == null || "".equals(name))) {
			Iterator<Teacher> iterator2 = retTeachers.iterator();
			while (iterator2.hasNext()) {
				tempTeacher = iterator2.next();
				if (!(limits.getTchrName().equals(tempTeacher.getTchrName()))) {
					set.add(tempTeacher);
				}
			}
			retTeachers.removeAll(set);
			set.clear();
		}
		String title = limits.getTchrTitle();
		if (!(title == null || "".equals(title))) {
			Iterator<Teacher> iterator3 = retTeachers.iterator();
			while (iterator3.hasNext()) {
				tempTeacher = iterator3.next();
				if (!(limits.getTchrTitle().equals(tempTeacher.getTchrTitle()))) {
					set.add(tempTeacher);
				}
			}
			retTeachers.removeAll(set);
			set.clear();
		}

		retTeachers.remove(null);
		return retTeachers;
	}

	@Override
	public boolean selectTchrBySchNum(String schNum) {
		teacherDao.findBySchNum(schNum);
		return true;
	}

	@Override
	public List<Teacher> selectAllTchrs() {
		return teacherDao.selectAllTchrs();
	}

	private TeacherDao teacherDao;

	@Override
	@Resource(name = "teacherDaoImpl")
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Teacher selectTeacherBySchNum(String schNum) {

		return teacherDao.findBySchNum(schNum);
	}

	private TeacherCourseDao teacherCourseDao;

	@Resource(name = "teacherCourseDaoImpl")
	public void setTeacherCourseDao(TeacherCourseDao teacherCourseDao) {
		this.teacherCourseDao = teacherCourseDao;
	}

	ImportExecl importExcel = new ImportExecl();

	@Override
	public boolean addTchrExcel(String path) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Teacher> tchrlist = new LinkedList<Teacher>();
		result = importExcel.read(path);
		String tchrNum = "";
		String tchrGender = "";
		String tchrPwd = "";
		Teacher teacher = null;
		Teacher teacherTemp = null;
		List<String> existTchr = new LinkedList<String>();// 用来记录需要更新的老师的工号
		List<String> errtchrIdent = new LinkedList<String>();
		result.remove(null);
		if (result.size() > 0) {
			for (int i = 3; i < result.size(); i++) {
				teacher = new Teacher();
				List<String> cellList = result.get(i);
				boolean flag = true;// 假设默认为空行
				if (cellList.size() > 0) {
					for (int m = 0; m < cellList.size(); m++) {
						if (!cellList.get(m).equals("")) {
							flag = false;// 只要有数据就不是空行
						}
					}
				}
				if (flag) {// 空行直接跳出当前循环
					continue;
				}
				tchrNum = cellList.get(2).toString();
				if (!tchrNum.equals("")) {
					teacherTemp = teacherDao.findBySchNum(tchrNum);
					if (teacherTemp != null) {
						// 若老师已存在，记录工号
						existTchr.add(tchrNum);
					}
					teacher.setTchrSchNum(tchrNum);
				}
				teacher.setTchrName(cellList.get(1));
				tchrGender = cellList.get(3);
				if (tchrGender.equals("男")) {
					teacher.setTchrGender(true);
				} else if (tchrGender.equals("女")) {
					teacher.setTchrGender(false);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date = null;
				try {
					date = sdf.parse(cellList.get(4).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				teacher.setTchrBirthday(date);
				teacher.setTchrDegree(cellList.get(5));
				teacher.setTchrTitle(cellList.get(6));
				teacher.setTchrMajor(cellList.get(7));
				teacher.setTchrGraduateSchool(cellList.get(8));
				date = null;
				try {
					date = sdf.parse(cellList.get(9).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				teacher.setTchrAttendDate(date);
				// 密码初始为工号
				tchrPwd = tchrNum;
				try {
					tchrPwd = ServiceUtils.md5(tchrPwd);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				teacher.setTchrPwd(tchrPwd);
				teacher.setIsManager(0);//初始为不是专业负责人
				teacher.setIsDelete(1);// 注意必须加此字段
				tchrlist.add(teacher);
			}
		}
		if ((tchrlist.size() > 0) && (existTchr.size() > 0)
				&& (errtchrIdent.size() == 0)) { // 有重复的老师
			List<Teacher> tchrlistRep = new LinkedList<Teacher>();// 重复的老师
			for (int i = 0; i < existTchr.size(); i++) { // 将重复的和未重复的分开
				String tchrNumTemp = "";
				for (int j = 0; j < tchrlist.size(); j++) {
					tchrNumTemp = tchrlist.get(j).getTchrSchNum();
					if (tchrNumTemp.equals(existTchr.get(i))) {
						tchrlistRep.add(tchrlist.get(j));
					}
				}
			}
			tchrlist.removeAll(tchrlistRep);
			if (tchrlist.size() > 0) {// 未重复的
				for (int m = 0; m < tchrlist.size(); m++) {
					teacherDao.addByAdmin(tchrlist.get(m));// add
				}
			}
			if (tchrlistRep.size() > 0) {// 重复的
				for (int m = 0; m < tchrlistRep.size(); m++) {
					teacherDao.updateByTeacher(tchrlistRep.get(m));// update
				}
			}
		} else if ((tchrlist.size() > 0) && (existTchr.size() == 0)
				&& (errtchrIdent.size() == 0)) { // 没有重复的老师
			for (int i = 0; i < tchrlist.size(); i++) {
				teacherDao.addByAdmin(tchrlist.get(i));// add
			}
		}
		return true;
	}

	@Override
	public boolean addTchrCursExcel(String path) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<TeacherCourse> tchrCurs = new LinkedList<TeacherCourse>();
		result = importExcel.read(path);
		String tchrNum = "";
		String cursNum = "";
		Teacher teacher = null;
		Course course = null;
		TeacherCourse teacherCourse = null;
		List<String> notExistTchr = new LinkedList<String>();
		List<String> notExistCurs = new LinkedList<String>();
		if (result.size() > 0) {
			for (int i = 1; i < result.size(); i++) {
				teacherCourse = new TeacherCourse();
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
				tchrNum = cellList.get(0).toString();
				if (!tchrNum.equals("")) {
					teacher = teacherDao.findBySchNum(tchrNum);
					if (teacher != null) {
						teacherCourse.setTeacher(teacher);
					} else {
						notExistTchr.add(tchrNum);
					}
				}
				cursNum = cellList.get(2);
				if (!cursNum.equals("")) {
					course = courseDao.findByNum(cursNum);
					if (course != null) {
						teacherCourse.setCourse(course);
					} else {
						notExistCurs.add(cursNum);
					}
				}
				teacherCourse.setTeacher(teacher);
				teacherCourse.setCourse(course);
				tchrCurs.add(teacherCourse);
			}
			if (notExistTchr.size() > 0) {
				String notExiString = "";
				for (int j = 0; j < notExistTchr.size(); j++) {
					notExiString += notExistTchr.get(j) + ",";
				}
				notExiString = notExiString.substring(0,
						notExiString.length() - 1);
				throw new TeacherNotExistException("工号为" + notExiString
						+ "的老师不存在");
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
		if ((tchrCurs.size() > 0) && (notExistTchr.size() == 0)
				&& (notExistCurs.size() == 0)) {
			Set<TeacherCourse> set = new LinkedHashSet<TeacherCourse>();
			teacherCourse = null;
			List<TeacherCourse> tchrCursTemp = teacherCourseDao
					.findAlltchrCurs();// 先获取数据库所有，然后去掉重复的
			Iterator<TeacherCourse> iterator1 = tchrCursTemp.iterator();
			Iterator<TeacherCourse> iterator2 = null;// excel
			Integer cursId1 = null;
			Integer tchrId1 = null;
			Integer cursId2 = null;
			Integer tchrId2 = null;
			while (iterator1.hasNext()) {
				teacherCourse = iterator1.next();
				cursId1 = teacherCourse.getCourse().getCursId();
				tchrId1 = teacherCourse.getTeacher().getTchrId();
				iterator2 = tchrCurs.iterator();
				while (iterator2.hasNext()) {
					teacherCourse = iterator2.next();
					cursId2 = teacherCourse.getCourse().getCursId();
					tchrId2 = teacherCourse.getTeacher().getTchrId();
					if (cursId1.equals(cursId2) && tchrId1.equals(tchrId2)) {
						set.add(teacherCourse);
					}
				}
			}
			tchrCurs.removeAll(set);
			set.clear();
			for (int i = 0; i < tchrCurs.size(); i++) {
				teacherDao.addTchrCurs(tchrCurs.get(i));
			}
		}
		return true;
	}

	@Override
	public List<Teacher> selectAllManagers() {
		return teacherDao.selectAllManagers();
	}

	@Override
	public boolean updateManager(List<Integer> managerIds) {
		List<Teacher> managers = teacherDao.selectAllManagers();
		//若存在，则先删除
		if(managers != null){
			for(int i=0;i<managers.size();i++){
				teacherDao.deleteManager(managers.get(i).getTchrId());
			}
		}
		//然后添加
		for(int i=0;i<managerIds.size();i++){
			teacherDao.addManager(managerIds.get(i));
		}
		return true;
	}
}
