package org.dzq.service;

import java.util.List;

import org.dzq.entity.Student;

public interface IStudentService {
	public Student queryStudentBySno(int sno) ;
	public List<Student> queryAllStudent() ;
	public boolean updateStudent(int sno,Student student) ;
	public boolean deleteStudent(int sno) ;
	public boolean addStudent(Student student) ;
	public List<Student> queryStudentsByPage(int currentPage, int pageSize);
	public int getTotalCount();
}
