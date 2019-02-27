package org.dzq.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dzq.dao.IStudentDao;
import org.dzq.entity.Student;
import org.dzq.util.DBUtil;

public class StudentDaoImpl implements IStudentDao{
	private static final String url = "jdbc:mysql://localhost:3306/books";
	private static final String user = "Tiger";
	private static final String password = "123456";
	//¸Ä
	public boolean updateStudentBySno(int sno,Student student) {
			String sql="update student set sname=?,sage=?,saddress=? where sno=?";
			Object[] params= {student.getSname(),student.getSage(),student.getSaddress(),sno};
			return DBUtil.executeUpdate(sql,params);
	}
	//É¾
	public boolean deleteStudentBySno(int sno) {
			String sql="delete from student where sno=?";
			Object[] params= {sno};
			return DBUtil.executeUpdate(sql,params);
	}
	//Ôö
	public boolean addStudent(Student student) {
			String sql="insert into student value(?,?,?,?)";
			Object[] params= {student.getSno(),student.getSname(),student.getSage(),student.getSaddress()};
			return DBUtil.executeUpdate(sql,params);
	}
	//²é
	public List<Student> queryAllStudents() {
		List<Student> students=new ArrayList<>();
		Student student=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from student";
			rs = DBUtil.executeQuery(sql,null);
			
			while(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student=new Student(no,name,age,address);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, pstmt, DBUtil.connection);
		}
	}
 	public boolean isExist(int sno) {
		return queryStudentBySno(sno)==null? false:true;
	}
	public Student queryStudentBySno(int sno) {
		Student student=null;
		Connection connection =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			String sql="select * from student where sno=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student=new Student(no,name,age,address);
			}
			return student;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
			}catch (SQLException e) { 
				e.printStackTrace();
			}
		}
	}
	@Override
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		String sql="select * from student order by sno asc limit ?,?";
		Object[] params= {(currentPage-1)*pageSize,pageSize};
		List<Student> students=new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()) {
				Student student=new Student(rs.getInt("sno"),rs.getString("sname"),rs.getInt("sage"),rs.getString("saddress"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	@Override
	public int getTotalCount() {
		String sql="select count(1) from student";
		return DBUtil.getAllCount(sql);
	}
}
