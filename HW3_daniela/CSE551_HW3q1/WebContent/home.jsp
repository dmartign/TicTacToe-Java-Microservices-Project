<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="hw3q1.model.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form method="POST" action="logout">
		<input type="submit" value="Logout">
	</form>
	<%
	    User user = (User) request.getSession().getAttribute("user");
	    out.write("Welcome, " + user.getName() + "&nbsp;&lt;" + user.getEmail() + "&gt;");
	%>
	<br />
	<%
	    out.write("You live at: " + user.getStreetAddress() + ", " + user.getState() + ", " + user.getZipcode());
	%>
	<br />
	<%
	    out.write("Your session id is: " + request.getSession().getId());
	%>
	<p>To see a list of projects of the users, click the following link: <a href="projects.jsp">Projects</a></p>
</body>
</html>