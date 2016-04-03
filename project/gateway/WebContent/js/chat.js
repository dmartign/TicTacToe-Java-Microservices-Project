function sendChat(){
	
	alert("Sending Chat");
	//var message = chatInput.value();
	var message = document.getElementById("chatInput").value; 
	alert("message is " + message);
	
}



$(function() {

var $chatInput = $('.chatInput'); // Input message input box
var connected = false;
var username = "user1";
var socket = io();



//need to change connected to true



// Adds the visual chat message to the message list
function addChatMessage (data, options) {
  // Don't fade the message in if there is an 'X was typing'
  var $typingMessages = getTypingMessages(data);
  options = options || {};
  if ($typingMessages.length !== 0) {
    options.fade = false;
    $typingMessages.remove();
  }

  var $usernameDiv = $('<span class="username"/>')
    .text(data.username)
    .css('color', getUsernameColor(data.username));
  var $messageBodyDiv = $('<span class="messageBody">')
    .text(data.message);

  var typingClass = data.typing ? 'typing' : '';
  var $messageDiv = $('<li class="message"/>')
    .data('username', data.username)
    .addClass(typingClass)
    .append($usernameDiv, $messageBodyDiv);

  addMessageElement($messageDiv, options);
}




// Sends a chat message
function sendMessage () {
	alert("in sendMessage");
	
  var message = $chatInput.val();
  // Prevent markup from being injected into the message
  message = cleanInput(message);
  // if there is a non-empty message and a socket connection
  if (message && connected) {
    $chatInput.val('');
    addChatMessage({
      username: username,
      message: message
    });
    // tell server to execute 'new message' and send along one parameter
    socket.emit('new message', message);
  }
}

//Prevents input from having injected markup
function cleanInput (input) {
  return $('<div/>').text(input).text();
}


//Focus input when clicking on the message input's border
$chatInput.click(function () {
	$chatInput.focus();
});









}); //end main function
