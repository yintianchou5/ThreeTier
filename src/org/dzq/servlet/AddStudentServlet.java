package org.dzq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzq.entity.Student;
import org.dzq.service.IStudentService;
import org.dzq.service.impl.StudentServiceImpl;

public class AddStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int no =Integer.parseInt(request.getParameter("sno")) ;
		String name = request.getParameter("sname");
		int age =Integer.parseInt(request.getParameter("sage")) ;
		String address = request.getParameter("saddress");
		Student student=new Student(no,name,age,address);
		IStudentService studentService=new StudentServiceImpl();
		boolean result = studentService.addStudent(student);
		PrintWriter out = response.getWriter();
		if(!result) {
			request.setAttribute("error", "adderror");
		}else {
			request.setAttribute("error", "noadderror");
		}
		request.getRequestDispatcher("QueryStudentByPageServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
