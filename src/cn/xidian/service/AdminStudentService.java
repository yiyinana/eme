package cn.xidian.service;

import java.util.List;
import java.util.Set;
import cn.xidian.dao.StudentDao;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentItem;
import cn.xidian.web.bean.AdminStuLimits;

public interface AdminStudentService {

	boolean addStudent(Student student);

	boolean deleteStudent(String schNum);

	boolean updateStudent(Student student);

	boolean selectStuBySchNum(String schNum);

	Student selectStudentBySchNum(String schNum);

	Set<Student> selectStudentByName(String name);

	void setStudentDao(StudentDao studentDao);

	List<Student> selectAllStus();

	Set<Student> selectStuLimits(AdminStuLimits limits);

	boolean addStuExcel(String path);

	boolean addStuCursExcel(String path);

	List<StudentItem> selectStuItemsBySchNum(String schNum);

	boolean judgeStuItem(StudentItem item);
}