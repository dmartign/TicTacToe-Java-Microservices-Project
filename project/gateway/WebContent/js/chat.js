
 var socket = io("http://localhost:3000");
 socket.emit('add user',"name");
	
function sendChat(){

	var message = document.getElementById("chatInput").value; 
	socket.emit('new message',message);
}

function addChatMessage(data){
	document.getElementById("chatBox").innerHTML = document.getElementById("chatBox").innerHTML + data.username + " : " +data.message + "<br/>";
}

socket.on('new message', function (data) {
    addChatMessage(data);
  });

socket.on('user joined',function(data){
	document.getElementById("chatBox").innerHTML = document.getElementById("chatBox").innerHTML + data.username + " has joined " + data.numUsers +  "<br/>";
});













