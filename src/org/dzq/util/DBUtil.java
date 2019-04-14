package org.dzq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dzq.entity.Student;

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/books";
	private static final String user = "Tiger";
	private static final String password = "123456";
	public static Connection connection =null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs =null;
	public static Connection getconnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);
	}
	public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		pstmt = getconnection().prepareStatement(sql); 
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
			}catch (SQLException e) { 
				e.printStackTrace();
			}
	}
	public static boolean executeUpdate(String sql,Object[] params) {
		int count =-1;
		try {
			createPreparedStatement(sql,params);
			count = pstmt.executeUpdate();
			if(count>0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			closeAll(null,pstmt,connection);
		}
	}
	public static ResultSet executeQuery(String sql,Object[] params) {
		List<Student> students=new ArrayList<>();
		Student student=null;
		try {
			createPreparedStatement(sql,params);
			rs = pstmt.executeQuery();
			return rs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getAllCount(String sql)  {
		int count =-1;
		try {
			pstmt = createPreparedStatement(sql, null);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, connection);
		}
		return count;
	}
}
