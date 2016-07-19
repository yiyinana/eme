package cn.xidian.dao.impl;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherDao;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.TeacherCourse;

@Component
public class TeacherDaoImpl implements TeacherDao {

	private SessionFactory sessionFactory;

	@Override
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Teacher findBySchNum(String tchrSchNum) {
		String sql = "from Teacher t where t.tchrSchNum=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, tchrSchNum);
		Teacher teacher = (Teacher) query.uniqueResult();
		return teacher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Teacher> findByName(String name) {
		Set<Teacher> teachers = new LinkedHashSet<Teacher>();
		String sql = "from Teacher t where t.tchrName = ? and t.isDelete=1 order by tchrId asc";
		Query query = currentSession().createQuery(sql).setString(0, name);
		teachers.addAll(query.list());
		return teachers;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Teacher> findByTitle(String title) {
		Set<Teacher> teachers = new LinkedHashSet<Teacher>();
		String sql = "from Teacher t where t.tchrTitle = ? and t.isDelete=1 order by tchrId asc";
		Query query = currentSession().createQuery(sql).setString(0, title);
		teachers.addAll(query.list());
		return teachers;
	}

	@Override
	public boolean updateByTeacher(Teacher teacher) {
		String sql = "update Teacher t set t.tchrName=?, t.tchrTitle=?, t.tchrPhone=?,"
				+ "t.tchrFax=?, t.tchrEmail=?, t.tchrOfficeAddr=? ,t.tchrSelflIntroduction=?,t.tchrBirthday=? "
				+ "where t.tchrSchNum=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, teacher.getTchrName())
				.setString(1, teacher.getTchrTitle())
				.setString(2, teacher.getTchrPhone())
				.setString(3, teacher.getTchrFax())
				.setString(4, teacher.getTchrEmail())
				.setString(5, teacher.getTchrOfficeAddr())
				.setString(6, teacher.getTchrSelflIntroduction())
				.setDate(7, teacher.getTchrBirthday())
				.setString(8, teacher.getTchrSchNum()).executeUpdate();
		return true;
	}

	@Override
	public boolean findBySchNumAndPwd(String tchrSchNum, String tchrPwd) {
		String sql = "from Teacher t where t.tchrSchNum=? and t.tchrPwd=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, tchrSchNum).setString(1, tchrPwd);
		Teacher teacher = (Teacher) query.uniqueResult();
		if (teacher != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addByAdmin(Teacher teacher) {
		currentSession().save(teacher);
		return true;
	}

	@Override
	public boolean deleteByAdmin(Integer id) {
		String hql = "update Teacher set isDelete=0 where tchrId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, id);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean updateByAdmin(Teacher teacher) {
		String sql = "update Teacher t set t.tchrName=?, t.tchrBirthday=?, t.tchrIdentNum=?,"
				+ "t.tchrDegree=?, t.tchrTitle=?, t.tchrMajor=? ,t.tchrGraduateSchool=?,t.tchrAttendDate=? "
				+",t.tchrPhone=?,t.tchrFax=?,t.tchrEmail=?,t.tchrOfficeAddr=?"
				+ "where t.tchrSchNum=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, teacher.getTchrName())
				.setDate(1, teacher.getTchrBirthday())
				.setString(2, teacher.getTchrIdentNum())
				.setString(3, teacher.getTchrDegree())
				.setString(4, teacher.getTchrTitle())
				.setString(5, teacher.getTchrMajor())
				.setString(6, teacher.getTchrGraduateSchool())
				.setDate(7, teacher.getTchrAttendDate())
				.setString(8, teacher.getTchrPhone())
				.setString(9, teacher.getTchrFax())
				.setString(10, teacher.getTchrEmail())
				.setString(11, teacher.getTchrOfficeAddr())
				.setString(12, teacher.getTchrSchNum()).executeUpdate();
		return true;
	}

	@Override
	public List<Teacher> selectAllTchrs() {
		String hql = "from Teacher where isDelete=1 order by tchrId asc";
		Query query = currentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Teacher> teachers = query.list();
		return teachers;
	}

	@Override
	public boolean addTchrCurs(TeacherCourse teacherCourse) {
		currentSession().save(teacherCourse);
		return true;
	}

	@Override
	public boolean modifyPassword(String num, String pwd) {
		String sql = "update Teacher t " + "set t.tchrPwd=? "
				+ "where t.tchrSchNum=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, pwd);
		query.setString(1, num);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> selectAllManagers() {
		List<Teacher> teachers = new LinkedList<Teacher>();
		String sql = "from Teacher t where t.isManager = 1 and t.isDelete=1 order by tchrId asc";
		Query query = currentSession().createQuery(sql);
		teachers.addAll(query.list());
		return teachers;
	}

	@Override
	public boolean addManager(Integer id) {
		String sql = "update Teacher t set t.isManager=1 "
				+ "where t.tchrId=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id).executeUpdate();
		return true;
	}

	@Override
	public boolean deleteManager(Integer id) {
		String sql = "update Teacher t set t.isManager=0 "
				+ "where t.tchrId=? and t.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, id).executeUpdate();
		return true;
	}

}
