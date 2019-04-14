package org.dzq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzq.entity.Student;
import org.dzq.service.IStudentService;
import org.dzq.service.impl.StudentServiceImpl;

public class UpdateStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int no =Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		int age =Integer.parseInt(request.getParameter("age")) ;
		String address = request.getParameter("address");
		Student student=new Student(name,age,address);
		IStudentService studentService=new StudentServiceImpl();
		boolean flag = studentService.updateStudent(no, student);
		if(flag) {
			response.sendRedirect("QueryStudentByPageServlet");
		}else {
			response.getWriter().println("ÐÞ¸ÄÑ§ÉúÊ§°Ü£¡£¡");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
