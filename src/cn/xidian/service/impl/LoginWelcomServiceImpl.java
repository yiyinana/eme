package cn.xidian.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.entity.Identity;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.User;
import cn.xidian.service.AdminService;
import cn.xidian.service.LoginWelcomService;
import cn.xidian.service.StudentService;
import cn.xidian.service.TeacherService;

@Component
public class LoginWelcomServiceImpl implements LoginWelcomService {

	private StudentService studentServiceImpl;
	private TeacherService teacherServiceImpl;
	private AdminService adminServiceImpl;

	@Resource
	public void setAdminServiceImpl(AdminService adminServiceImpl) {
		this.adminServiceImpl = adminServiceImpl;
	}

	@Resource
	public void setStudentServiceImpl(StudentService studentServiceImpl) {
		this.studentServiceImpl = studentServiceImpl;
	}

	@Resource
	public void setTeacherServiceImpl(TeacherService teacherServiceImpl) {
		this.teacherServiceImpl = teacherServiceImpl;
	}

	@Override
	public User loginValidate(User user) {

		String schNum = user.getSchNum();
		String pwd = user.getPwd();

		boolean flag = false;
		Identity identity = user.getIdentity();
		switch (identity) {
		case STUDENT:
			flag = studentServiceImpl.loginValidate(schNum, pwd);
			if (flag) {
				user.setUserName(studentServiceImpl.selectInfBySchNum(
						user.getSchNum()).getStuName());
				return user;
			}
			break;
		case TEACHER:
			flag = teacherServiceImpl.loginValidate(schNum, pwd);
			if (flag) {
				Teacher teacher = teacherServiceImpl.selectInfBySchNum(user.getSchNum());
				user.setUserName(teacher.getTchrName());
				user.setIsManager(teacher.getIsManager());//是否为管理员
				user.setIsCounselor(teacher.getIsCounselor());
				return user;
			}
			break;
		case ADMIN:
			flag = adminServiceImpl.loginValidate(schNum, pwd);
			if (flag) {
				user.setUserName(adminServiceImpl.selectInfBySchNum(
						user.getSchNum()).getAdminName());
				return user;
			}
			break;
		default:
			return null;
		}

		return null;
	}

}
