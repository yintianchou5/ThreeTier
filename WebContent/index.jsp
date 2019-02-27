<%@page import="org.dzq.entity.Page"%>
<%@page import="org.dzq.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息列表</title>
</head>
<body>	
		<%
			String error=(String)request.getAttribute("error");
			if(error!=null){
				if(error.equals("adderror")){
					out.println("增加失败！");
				}else if(error.equals("noadderror")){
					out.println("增加成功！");
				}
			}	
		
		%>
		<table border="1px">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>操作</th>
			</tr>
			<%
				Page paee= (Page)request.getAttribute("paee");
				for(Student student:paee.getStudents()){
			%>
				<tr>
					<td><a href="QueryStudentServlet?sno=<%=student.getSno() %>"><%=student.getSno() %></a></td>
					<td><%=student.getSname() %></td>
					<td><%=student.getSage() %></td>
					<!-- <td><%=student.getSaddress() %></td> -->
					<td><a href="DeleteStudentServlet?sno=<%=student.getSno() %>">删除</a></td>
				</tr>
			<%
				}
			%>
		</table>
		<a href="add.jsp">新增</a><br/>
		<%
				if(paee.getCurrentPage()==1){
		%>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getCurrentPage()+1%>">下一页</a>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getTotalPage() %>">尾页</a>
					
		<% 	
				}else if(paee.getCurrentPage()==paee.getTotalPage()){
		%>
				<a href="QueryStudentByPageServlet?currentPage=1">首页</a>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getCurrentPage()-1%>">上一页</a>
		<%	
				}else{
		%>		
				<a href="QueryStudentByPageServlet?currentPage=1">首页</a>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getCurrentPage()-1%>">上一页</a>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getCurrentPage()+1%>">下一页</a>
				<a href="QueryStudentByPageServlet?currentPage=<%=paee.getTotalPage() %>">尾页</a>
		<%
				} 
		%><br/>
		每页显示
		<select>
			<option value="3" >3</option>
			<option value="5">5</option>
			<option value="10">10</option>
		</select>
		条
			
		
		
		
</body>
</html>