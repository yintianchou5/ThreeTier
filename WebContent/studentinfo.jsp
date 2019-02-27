<%@page import="org.dzq.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生个人信息</title>
</head>
<body>
		<%
			Student student=(Student)request.getAttribute("student");
			
		%>
		<form action="UpdateStudentServlet">
			学号：<input type="text" name="no" value="<%=student.getSno() %>" readonly="readonly"/><br/>
			姓名：<input type="text" name="name" value="<%=student.getSname() %>"/><br/>
			年龄：<input type="text" name="age" value="<%=student.getSage() %>"/><br/>
			地址：<input type="text" name="address" value="<%=student.getSaddress()%>"/><br/>
			<input type="submit" value="修改">
			<a href="QueryStudentByPageServlet">返回</a>
		</form>
</body>
</html>