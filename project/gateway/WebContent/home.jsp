<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="tbd.gateway.model.domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
	.CHAT {
		word-wrap:break-word;
		table-layout:fixed;
		overflow-y:auto;
		width:100%;
		max-width:100%;
		height:10em;
	}
</style>

<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://localhost:3000/socket.io/socket.io.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
<script type="text/javascript" src="js/gamelist.js"></script>
<script type="text/javascript" src="js/game.js"></script>
<script type="text/javascript" src="js/glMatrix-0.9.5.min.js"></script>
<script id="shader-fs" type="x-shader/x-fragment">
    precision mediump float;
// http://learningwebgl.com/blog/?p=134
	//varying vec4 vColor; 	// new
	
    void main(void) {
        gl_FragColor = vec4(0.5, 0.5, 1.0, 1.0); //vColor;
    }
</script>

<script id="shader-vs" type="x-shader/x-vertex">
    attribute vec3 aVertexPosition;
	//attribute vec4 aVertexColor;	// new

    uniform mat4 uMVMatrix;
    uniform mat4 uPMatrix;
	
	//varying vec4 vColor;	// new

    void main(void) {
        gl_Position = uPMatrix * uMVMatrix * vec4(aVertexPosition, 1.0);
		//vColor = aVertexColor; // new
    }
</script>

</head>
<body onload="webGLStart()">
	
	<form method="POST" action="logout">
		<input type="submit" value="Logout">
	</form>
	<%
	    User user = (User) request.getSession().getAttribute("user");
	    out.write("Welcome, " + user.getName());
	%>
	<br />
	<div class="container-fluid">
	<div class="row-fluid">
	<input id="challengeinput" type="text"/><input type="button" value="Challenge" onclick="createGame()"/>
	<table id="gamelist"></table>
	</div>
	<div class="row-fluid">
	<canvas id="tictactoe-canvas" style="border: none;"
					width="600" height="600"></canvas>
					</div>
					<div class="row-fluid">
	<div class="CHAT" id="chatbox"></div>
	<br /><input style="width: 75%;" type="text" id="chatInput" onkeydown="checkForEnter()" /><input id="chatEnter" style="width: 20%;" type="button" value="Send" onclick="sendChat()" />
	</div>
	</div>
	<script type="text/javascript" src="js/webgl.js"></script>
</body>
	

</html>