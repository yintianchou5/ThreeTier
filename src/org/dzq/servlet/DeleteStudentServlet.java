package org.dzq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzq.service.IStudentService;
import org.dzq.service.impl.StudentServiceImpl;

public class DeleteStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int no =Integer.parseInt(request.getParameter("sno")) ;
		IStudentService studentService=new StudentServiceImpl();
		boolean flag = studentService.deleteStudent(no);
		if(flag) {
			//response.getWriter().println("删除学生成功！！");
			response.sendRedirect("QueryStudentByPageServlet");
		}else {
			response.getWriter().println("删除学生失败！！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
