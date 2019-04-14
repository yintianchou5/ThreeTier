package org.dzq.service.impl;

import java.util.List;

import org.dzq.dao.IStudentDao;
import org.dzq.dao.impl.StudentDaoImpl;
import org.dzq.entity.Student;
import org.dzq.service.IStudentService;

//ÒµÎñÂß¼­²ã
public class StudentServiceImpl implements IStudentService{
	
	IStudentDao studentDao = new StudentDaoImpl();
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	public List<Student> queryAllStudent() {
		return studentDao.queryAllStudents();
	}
	public boolean updateStudent(int sno,Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno, student);
		}else {
			return false;
		}
	}
	public boolean deleteStudent(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno);
		}else {
			return false;
		}
	}
	public boolean addStudent(Student student) {
		 
		if(!studentDao.isExist(student.getSno())) {
			return studentDao.addStudent(student);
		}else {
			return false;
		}
		 
	}
	@Override
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		return studentDao.queryStudentsByPage(currentPage, pageSize);
	}
	@Override
	public int getTotalCount() {
		return studentDao.getTotalCount();
	}
}
