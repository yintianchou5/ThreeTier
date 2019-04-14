package org.dzq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzq.entity.Student;
import org.dzq.service.IStudentService;
import org.dzq.service.impl.StudentServiceImpl;

public class QueryStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int no =Integer.parseInt(request.getParameter("sno")) ;
		IStudentService studentService=new StudentServiceImpl();
		Student student = studentService.queryStudentBySno(no);
		request.setAttribute("student", student);
		request.getRequestDispatcher("studentinfo.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
