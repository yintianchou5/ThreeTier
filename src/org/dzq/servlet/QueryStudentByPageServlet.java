package org.dzq.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzq.entity.Page;
import org.dzq.entity.Student;
import org.dzq.service.IStudentService;
import org.dzq.service.impl.StudentServiceImpl;

public class QueryStudentByPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService studentService=new StudentServiceImpl();
		Page page=new Page();
		int count = studentService.getTotalCount();
		page.setTotalCount(count);
		String cPage = request.getParameter("currentPage");
		if(cPage==null) {
			cPage="1";
		}
		int currentPage =Integer.parseInt(cPage);
		page.setCurrentPage(currentPage);
		int pageSize=3;
		page.setPageSize(pageSize);
		List<Student> students = studentService.queryStudentsByPage(currentPage, pageSize);
		page.setStudents(students);
		request.setAttribute("paee", page);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		//System.out.println(students);
		//System.out.println(count);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
