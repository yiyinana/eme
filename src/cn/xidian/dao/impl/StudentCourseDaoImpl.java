package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentCourseDao;
import cn.xidian.entity.StudentCourse;

@Component
public class StudentCourseDaoImpl implements StudentCourseDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByNumAndTerm(String stuNum, String currTerm) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.student.stuSchNum=? and sc.course.cursCurrTerm=? and sc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, stuNum).setString(1, currTerm);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByNum(String stuNum) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.student.stuSchNum=? and sc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, stuNum);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByTchrNum(String tchrNum) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.course.cursId in (select tc.course.cursId from TeacherCourse tc where tc.teacher.tchrSchNum=?)";
		Query query = currentSession().createQuery(sql);
		query.setString(0, tchrNum);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByClaName(String claName) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.student.clazz.claId in (select s.clazz.claId from Student s where s.clazz.claName=?) and sc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, claName);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByCursName(String cursName) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.course.cursName=? and sc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, cursName);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findAll() {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.course.isDelete=1";
		Query query = currentSession().createQuery(sql);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByLimit(String claName, String curName,
			String tchrSchNum) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		// 求cursId交集
		String sql = "select sc.* from student_course sc left join student on student.stuId=sc.stuId where sc.stuId in "
				+ "(select s.stuId from student s left join clazz on clazz.claId=s.claId where clazz.claName=?) and sc.cursId in "
				+ "(select * from (select distinct cursId from course c where c.cursName=? and c.isDelete=1 union all "
				+ "select distinct cursId from teacher_course tc left join teacher on teacher.tchrId=tc.tchrId "
				+ "where teacher.tchrSchNum=? and teacher.isDelete=1) "
				+ "temp GROUP BY cursId HAVING COUNT(cursId) = 2) "
				+ "order by sc.stuCursId asc";
		SQLQuery query = currentSession().createSQLQuery(sql).addEntity(
				StudentCourse.class);
		query.setString(0, claName).setString(1, curName)
				.setString(2, tchrSchNum);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByLimitAllClazz(String curName,
			String tchrSchNum) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		// 求cursId交集
		String sql = "select sc.* from student_course sc where sc.cursId in "
				+ "(select * from (select distinct cursId from course c where c.cursName=? and c.isDelete=1 union all "
				+ "select distinct cursId from teacher_course tc left join teacher on teacher.tchrId=tc.tchrId "
				+ "where teacher.tchrSchNum=? and teacher.isDelete=1) "
				+ "temp GROUP BY cursId HAVING COUNT(cursId) = 2) "
				+ "order by sc.stuCursId asc";
		SQLQuery query = currentSession().createSQLQuery(sql).addEntity(
				StudentCourse.class);
		query.setString(0, curName).setString(1, tchrSchNum);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> findByCursId(Integer cursId) {
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		String sql = "from StudentCourse sc where sc.course.cursId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId);
		stuCurs.addAll(query.list());
		return stuCurs;
	}

	@Override
	public StudentCourse selectByCursIdAndStuId(Integer cursId, Integer stuId) {
		String sql = "from StudentCourse sc where sc.course.cursId=? and sc.student.stuId=? ";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0, cursId).setInteger(1, stuId);
		StudentCourse sc = (StudentCourse) query.uniqueResult();
		return sc;
	}

	@Override
	public boolean add(StudentCourse sc) {
		currentSession().save(sc);
		return true;
	}

	@Override
	public boolean delete(StudentCourse sc) {
		currentSession().delete(sc);
		return true;
	}
}
