
var io = require('socket.io').listen(3000);

//chatroom
var numUsers = 0;

io.on('connection', function(socket){
	
	var addedUser = false;
	
	
	//when client emits 'new message', this listens and executes
	socket.on('new message',function(data){
		io.emit('new message',{
			username: socket.username,
			message: data
		});
	});
	
	//when the client emits 'add user', this listens and executes
	socket.on('add user',function(username){
		if (addedUser) return;
		
		//we store the username in the socket session for this client
		socket.username = username;
		++numUsers;
		addedUser = true;
 		socket.emit('login',{
			numUsers:numUsers
		});
		//echo globally that a person has connected
		socket.broadcast.emit('user joined',{
			username:socket.username,
			numUsers:numUsers
		});
	});
	
	
	
	//when the user disconnects, do this
	socket.on('disconnect',function(){
		if(addedUser){
			--numUsers;
		
		//echo globally that this client has left
		socket.broadcast.emit('user left',{
			username:socket.username,
			numUsers: numUsers
		});
		}
	});
});
