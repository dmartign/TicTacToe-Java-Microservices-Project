//inspired by https://github.com/socketio/socket.io/tree/master/examples/chat

var io = require('socket.io').listen(3000);
var cookieParser = require('socket.io-cookie');
var http = require('http');
io.use(cookieParser);

//chatroom
var numUsers = 0;

io.on('connection', function(socket){
	console.log('connection');
	var addedUser = false;
	
	
	//when client emits 'new message', this listens and executes
	socket.on('new message',function(data){
		io.emit('new message',{
			username: socket.username,
			message: data
		});
	});
	
	//Register this socket to this user
	function registerUser(username){
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
	}
	var cookie=socket.handshake.headers['cookie'];
	console.log(`Cookie: ${cookie.userToken}`);
	var user;
	http.get('http://localhost:9001/authentication/login/' + cookie.userToken, (res) => {
		// consume response body
		res.on('data',(data)=>{
			user = JSON.parse(data.toString('utf8'));
			console.log(`${user.username} Connected`);
			registerUser(user.username);
		});
	}).on('error', (e) => {
		console.log(`Got error: ${e.message}`);
	});
	
	
	//when the user disconnects, do this
	socket.on('disconnect',function(){
		console.log(`${user.username} Disconnected`);
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
