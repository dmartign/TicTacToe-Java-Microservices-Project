<!-- Note: WebGL basic code and ideas was obtained from tutorials, especially http://learningwebgl.com/blog/?p=28 -->

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

<!--  shaders initialize webGL drawing, rest is done in webgl.js -->
<script id="shader-fs" type="x-shader/x-fragment">
    precision mediump float;
	
    void main(void) {
        gl_FragColor = vec4(0.5, 0.5, 1.0, 1.0);
    }
</script>

<script id="shader-vs" type="x-shader/x-vertex">
    attribute vec3 aVertexPosition;

    uniform mat4 uMVMatrix;
    uniform mat4 uPMatrix;
	
    void main(void) {
        gl_Position = uPMatrix * uMVMatrix * vec4(aVertexPosition, 1.0);
    }
</script>
</head>

<body onload="webGLStart()">
	<div class="col-xs-12" style="height:10px;"></div>
		<div class="col-xs-12 col-sm-6 col-md-8">
			<h2 class="text-center text-info">Tic Tac Toe Game</h2>
		</div>
		<div class="col-xs-6 col-md-4">
			<form method="POST" action="logout">
				<button type="submit" class="btn pull-right">Logout</button>
			</form>
		</div>
	<div class="col-xs-12" style="height:20px;"></div>
	<hr>
	<h3>
	<%
	    User user = (User) request.getSession().getAttribute("user");
	    out.write("Welcome, " + user.getName());
	%>
	</h3>
	<br />
	<div class="col-xs-12" style="height:10px;"></div>
	<div class="container-fluid">
		<div class="row-fluid">
		<div class="span9">
			<input id="challengeinput" type="text"/>
			<button type="button" class="btn" onclick="createGame()">Challenge</button>
			<table id="gamelist"></table>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-8" style="height:10px;"></div>
		<div class="col-xs-12 col-sm-6 col-md-8">
			<canvas id="tictactoe-canvas" style="border: none;" width="600" height="600"></canvas>
		</div>
		<div class="col-xs-6 col-md-4">
			<div class="CHAT" id="chatbox"></div>
			<br />
			<input style="width: 75%;" type="text" id="chatInput" onkeydown="checkForEnter()" />
			<button type="submit" class="btn" id="chatEnter" onclick="sendChat()">Send</button>
		</div>
		<div class="col-xs-12" style="height:50px;"></div>
		</div>
	</div>
	<script type="text/javascript" src="js/webgl.js"></script>
</body>
	
</html>