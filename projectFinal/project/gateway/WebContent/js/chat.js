var socket = io("http://localhost:3000");
 
function sendChat(){
	var message = document.getElementById("chatInput").value; 
	socket.emit('new message',message);
	document.getElementById("chatInput").value = "";
	document.getElementById("chatInput").focus();
}

function addChatMessage(data){
	document.getElementById("chatbox").innerHTML = document.getElementById("chatbox").innerHTML + data.username + " : " +data.message + "<br/>";
}

function tryscrollDown(id){
	 var objDiv = document.getElementById(id);
	 try{
		 objDiv.scrollTop = objDiv.scrollHeight;
	 }
	 catch(e)
	 {
		 var f = document.createElement("input");
		 if (f.setAttribute) f.setAttribute("type","text")
		 if(objDiv.appendChild) objDiv.appendChild(objDiv);
		 f.style.width = "0px";
		 f.style.height = "0px";
		 if (f.focus) f.focus();
		 if (objDiv.removeChild) objDiv.removeChild(f);
	 }
}

function checkForEnter(event){
	//alert("right here");
	  //if (event.which == 13 || event.keyCode == 13) {
	if(!event){event = window.event;}
	if(event.keyCode == 13 || event.which == 13){
		document.getElementById("chatEnter").click();
		
      return false;
  }
  return true;
	
}

socket.on('new message', function (data) {
    addChatMessage(data);
    tryscrollDown("chatbox");
  });

socket.on('user joined',function(data){
	document.getElementById("chatbox").innerHTML = document.getElementById("chatbox").innerHTML + data.username + " has joined " + data.numUsers +  "<br/>";
});