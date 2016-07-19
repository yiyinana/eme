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

import cn.xidian.dao.CourseDao;
import cn.xidian.entity.Course;

@Component("courseDaoImpl")
public class CourseDaoImpl implements CourseDao {
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Course findByNum(String cursNum) {
		String sql = "from Course c where c.cursNum=? and isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursNum);
		Course course = (Course) query.uniqueResult();
		return course;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Course> findAllCurs() {
		Set<Course> courses = new LinkedHashSet<Course>();
		String sql = "from Course c where c.isDelete=1 order by cursId asc";
		Query query = currentSession().createQuery(sql);
		courses.addAll(query.list());
		return courses;
	}

	@Override
	public boolean updateByAdmin(Course course) {
		String hql = "update Course c set c.cursNum=?,c.cursName=?,c.cursEngName=?,c.cursCredit=?,"
				+ "c.cursClassHour=?,c.cursTerm=?,c.cursProperty=?,c.cursApplMajor=?,"
				+ "c.cursPreCourses=?,c.cursIntro=?,c.dept.deptId=?,c.teacher.tchrId=? where c.cursId=?";
		Query query = currentSession().createQuery(hql)
				.setString(0, course.getCursNum())
				.setString(1, course.getCursName())
				.setString(2, course.getCursEngName())
				.setDouble(3, course.getCursCredit())
				.setString(4, course.getCursClassHour())
				.setString(5, course.getCursTerm())
				.setString(6, course.getCursProperty())
				.setString(7, course.getCursApplMajor())
				.setString(8, course.getCursPreCourses())
				.setString(9, course.getCursIntro())
				.setInteger(10, course.getDept().getDeptId())
				.setInteger(11, course.getTeacher().getTchrId())
				.setInteger(12, course.getCursId());
		query.executeUpdate();
		return true;
	}
	
	@Override
	public boolean updateByTchr(Course course) {
		String hql = "update Course c set c.cursNum=?,c.cursName=?,c.cursEngName=?,c.cursCredit=?,"
				+ "c.cursClassHour=?,c.cursTerm=?,c.cursProperty=?,c.cursApplMajor=?,"
				+ "c.cursPreCourses=?,c.cursIntro=?,c.dept.deptId=?,c.type=? where c.cursId=?";
		Query query = currentSession().createQuery(hql)
				.setString(0, course.getCursNum())
				.setString(1, course.getCursName())
				.setString(2, course.getCursEngName())
				.setDouble(3, course.getCursCredit())
				.setString(4, course.getCursClassHour())
				.setString(5, course.getCursTerm())
				.setString(6, course.getCursProperty())
				.setString(7, course.getCursApplMajor())
				.setString(8, course.getCursPreCourses())
				.setString(9, course.getCursIntro())
				.setInteger(10, course.getDept().getDeptId())
				.setString(11, course.getType())
				.setInteger(12, course.getCursId());
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean deleteByCursId(Integer cursId) {
		String hql = "update Course c set c.isDelete=0 where c.cursId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, cursId);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean addByAdmin(Course course) {
		currentSession().save(course);
		return true;
	}

	@Override
	public boolean updateCursNote(Course course) {
		String sql = "update Course c " + "set c.cursNote1=?,c.cursNote2=? "
				+ "where c.cursId=? and c.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, course.getCursNote1());
		query.setString(1, course.getCursNote2());
		query.setInteger(2, course.getCursId());
		query.executeUpdate();
		return true;
	}

	@Override
	public Course findByNameAndTerm(String cursName) {
		String sql = "from Course c where c.cursName=? and c.isDelete=1 order by cursId asc";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursName);
		Course course = (Course) query.uniqueResult();
		return course;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByTerm() {
		List<Course> curs = new LinkedList<Course>();
		String sql = "from Course c where c.isDelete=1 order by cursId asc";
		Query query = currentSession().createQuery(sql);
		curs.addAll(query.list());
		return curs;
	}

/*	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByIds(List<Integer> cursId) {
		List<Course> curs = new LinkedList<Course>();
		String sql = "from Course c where c.cursId in (:cursId) order by cursId asc";
		Query query = currentSession().createQuery(sql);
		query.setParameterList("cursId", cursId);
		curs.addAll(query.list());
		return curs;
	}*/

	@Override
	public Course findById(Integer cursId) {
		String sql = "from Course c where c.cursId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId);
		Course course = (Course) query.uniqueResult();
		return course;
	}

	@Override
	public boolean updateCursNoteById(Course course) {
		String sql = "update Course c " + "set c.cursNote1=?,c.cursNote2=? "
				+ "where c.cursId=? and c.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, course.getCursNote1());
		query.setString(1, course.getCursNote2());
		query.setInteger(2, course.getCursId());
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> selectTchChargeCursByTchrNum(String tchrNum) {
		List<Course> curs = new LinkedList<Course>();
		String sql = "from Course c where c.isDelete=1 and c.teacher.tchrId=(select tchrId from Teacher as tchrId where tchrSchNum=? and isDelete=1) order by cursId asc";
		Query query = currentSession().createQuery(sql).setString(0, tchrNum);
		curs.addAll(query.list());
		return curs;
	}

	@Override
	public Course findByName(String cursName) {
		String sql = "from Course c where c.cursName=? and isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursName);
		Course course = (Course) query.uniqueResult();
		return course;
	}

}
