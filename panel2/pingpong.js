const port = 9002;
var jsonBody = require("body/json")
var sendJson = require("send-data/json")

require('http').createServer((req, res) => {
	function send(err, body){
		if(body != null && body.message == "ping"){
			sendJson(req, res, {"message":"pong"});
		} else {
			sendJson(req, res, {"message":"Can't pong that"});	
		}
	}
	if(req.method == "POST"){
		jsonBody(req, res, send);
	} else {
		sendJson(req, res, {"message":"Not a POST"});
	}
}).listen(port,() => {
	console.log(`Server running at ${port}`);
});