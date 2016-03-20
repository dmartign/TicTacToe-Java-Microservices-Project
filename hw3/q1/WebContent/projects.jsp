<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import="hw3q1.model.domain.*"%>
    <%@page import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="logout">
		<input type="submit" value="Logout">
	</form>
	<% 
		List<Project> projList = (List<Project>) request.getAttribute("projList");
	    
	for(Project project : projList){%>
		Student: 
		
		<% 
		out.write(project.getStudentName());%>
		<br/>
		Project:
		<%out.write(project.getProjectName()); %>
		<br/>
		Abstract:
		<%out.write(project.getAbstractText()); %>
		<br/>
		<br/>
		<% 
	}%>
	
</body>
</html>