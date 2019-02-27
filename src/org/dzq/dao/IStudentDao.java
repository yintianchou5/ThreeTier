package org.dzq.dao;

import java.util.List;

import org.dzq.entity.Student;

public interface IStudentDao {
	public boolean updateStudentBySno(int sno,Student student);
	public boolean deleteStudentBySno(int sno);
	public boolean addStudent(Student student);
	public List<Student> queryAllStudents();
	public boolean isExist(int sno);
	public Student queryStudentBySno(int sno);
	public int getTotalCount();
	public List<Student> queryStudentsByPage(int currentPage,int pageSize);
}
