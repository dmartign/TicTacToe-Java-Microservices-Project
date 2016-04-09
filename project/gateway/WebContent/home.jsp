<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="tbd.gateway.model.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	.CHAT {
		word-wrap:break-word;
		overflow-y:auto;
		width:100%;
		height:100px;
	
	}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://localhost:3000/socket.io/socket.io.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
</head>
<body>
	
	<form method="POST" action="logout">
		<input type="submit" value="Logout">
	</form>
	<%--
	    User user = (User) request.getSession().getAttribute("user");
	    out.write("Welcome, " + user.getName() + "&nbsp;&lt;" + user.getEmail() + "&gt;");
	--%>
	<br />
	<%--
	    out.write("Your session id is: " + request.getSession().getId());
	--%>
	
	
	<input type="button" value = "Scroll" onclick="tryscrollDown('chatBox')" />
	<br/>
	<table style="width:90%;">
		<tr>
			<td style="width:67%;border-color: red;border-style: solid;">
				<table>
					<tr><td>X</td><td>X</td><td>X</td></tr>
					<tr><td>X</td><td>X</td><td>X</td></tr>
					<tr><td>X</td><td>X</td><td>X</td></tr>
				</table>
				
			</td>
			
			
	  		<td style="width:33%;border-color: green;border-style: solid;">
	  			<div class="CHAT" id="chatBox">
	  			</div><br/>
	  				<input style="width:75%;" type="text" id="chatInput" onkeydown="checkForEnter()"/><input id="chatEnter" style="width:20%;" type="button" value="Send" onclick="sendChat()"/>
	  			
<%-- 
 <input style="width:75%;" type="text" id="chatInput"/><input style="width:20%;" type="button" value="Send" onclick="sendChat()"/>
  --%> 
	  		</td>
		</tr>
		
		<%-- 
		<tr>
		  		<td  style="width:33%;">
		  			<div class="CHAT" id="chatBox2"><br/></div><br/><input style="width:75%;" type="text" id="chatInput" onkeydown="checkForEnter()"/><input id="chatEnter" style="width:20%;" type="button" value="Send" onclick="sendChat()"/>
		  		</td>
		</tr>
	--%>
	</table>
</body>
	

</html>