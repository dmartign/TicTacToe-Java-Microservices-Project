<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="tbd.gateway.model.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script type="text/javascript" src="js/chat.js"></script>
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
	    out.write("Your session id is: " + request.getSession().getId());
	%>
	<br/>
	<table style="width:100%;">
		<tr>
			<td style="width:67%;border-color: red;border-style: solid;">
				<table>
					<tr><td>X</td><td>X</td><td>X</td></tr>
					<tr><td>X</td><td>X</td><td>X</td></tr>
					<tr><td>X</td><td>X</td><td>X</td></tr>
				</table>
				
			</td>
			
			
			<td style="width:33%;border-color: green;border-style: solid;"><div id="chatBox">Me: Awesome<br/>You: Less Awesome</div><br/><input style="width:75%;" type="text" id="chatInput"/><input style="width:20%;" type="button" value="Send" onclick="sendChat()"/></td>
		</tr>
	
	</table>
</body>
	

</html>