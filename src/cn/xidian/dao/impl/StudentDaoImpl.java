package cn.xidian.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentDao;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;

@Component("studentDaoImpl")
public class StudentDaoImpl implements StudentDao {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean findBySchNumAndPwd(String stuSchNum, String stuPwd) {
		String sql = "from Student s where s.stuSchNum=? and s.isDelete=1 and s.stuPwd=?";
		Query query = currentSession().createQuery(sql);
		query.setString(0, stuSchNum).setString(1, stuPwd);
		Student student = (Student) query.uniqueResult();
		if (student != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Student findBySchNum(String stuSchNum) {
		String sql = "from Student s where s.stuSchNum=? and s.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, stuSchNum);
		Student student = (Student) query.uniqueResult();
		return student;

	}

	@Override
	public boolean updateByStudent(Student student) {

		String sql = "update Student s "
				+ "set s.stuNativePlace=?, s.stuBirthday=?, s.stuPhone=?, s.stuDomiPhone=?, s.stuMail=?, s.stuCommAddr=?,s.selfIntroduce=?,s.selfEngIntroduce=?,s.stuName=? "
				+ "where s.stuSchNum=? and s.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, student.getStuNativePlace());
		query.setDate(1, student.getStuBirthday());
		query.setString(2, student.getStuPhone());
		query.setString(3, student.getStuDomiPhone());
		query.setString(4, student.getStuMail());
		query.setString(5, student.getStuCommAddr());
		query.setString(6, student.getSelfIntroduce());
		query.setString(7, student.getSelfEngIntroduce());
		query.setString(8, student.getStuName());
		query.setString(9, student.getStuSchNum());
		query.executeUpdate();
		return true;

	}
	
	public boolean updateGoal(Student student) {
		String sql = "update Student s "
				+ "set s.shortGoal=?,s.midGoal=?,s.longGoal=? "
				+ "where s.stuSchNum=? and s.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0, student.getShortGoal());
		query.setString(1, student.getMidGoal());
		query.setString(2, student.getLongGoal());
		query.setString(3, student.getStuSchNum());
		query.executeUpdate();
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> findByName(String name) {
		Set<Student> students = new LinkedHashSet<Student>();
		String sql = "from Student s where s.stuName = ? and s.isDelete=1 order by stuId asc";
		Query query = currentSession().createQuery(sql).setString(0, name);
		students.addAll(query.list());
		return students;
	}

	@Override
	public boolean addByAdmin(Student student) {
		currentSession().save(student);
		return true;
	}

	@Override
	public boolean deleteByAdmin(Student student) {
		String sql = "update Student s "
				+ "set s.isDelete=0 "
				+ "where s.stuId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0,student.getStuId());
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean updateByAdmin(Student student) {
		currentSession().update(student);
		return true;
	}

	@Override
	public boolean deleteByAdmin(Integer id) {
		String sql = "update Student s "
				+ "set s.isDelete=0 "
				+ "where s.stuId=?";
		Query query = currentSession().createQuery(sql);
		query.setInteger(0,id);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<Student> selectAllStus() {
		String hql = "from Student s where s.isDelete=1 order by stuId asc";
		Query query = currentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Student> students = query.list();
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> findByClazz(Integer clazzId) {
		Set<Student> students = new LinkedHashSet<Student>();
		String sql = "";
		if (clazzId == null) {
			sql = "from Student s where s.isDelete=1 order by stuId asc";
			Query query = currentSession().createQuery(sql);
			students.addAll(query.list());
		} else {
			sql = "from Student where claId = ? order by stuId asc";
			Query query = currentSession().createQuery(sql);
			query.setInteger(0, clazzId);
			students.addAll(query.list());
		}
		return students;
	}

	@Override
	public boolean modifyPassword(String num, String pwd) {
		String sql = "update Student s "
				+ "set s.stuPwd=? "
				+ "where s.stuSchNum=? and s.isDelete=1";
		Query query = currentSession().createQuery(sql);
		query.setString(0,pwd);
		query.setString(1,num);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentCourse> selectStuAllGradesById(Integer id) {
		// TODO Auto-generated method stub
		String sql="from StudentCourse where stuId=?";
		Query query =currentSession().createQuery(sql);
		query.setInteger(0, id);
		List<StudentCourse> studentCourses=query.list();
		return studentCourses;
	}

	@Override
	public EvaluateResult selectEvaluateResult(Integer stuId, String schoolYear) {
		// TODO Auto-generated method stub
		String sql="from EvaluateResult where stuId=? and schoolYear=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, stuId);
		query.setString(1, schoolYear);
		EvaluateResult evaluateResult=(EvaluateResult) query.uniqueResult();
		return evaluateResult;
	}
}
