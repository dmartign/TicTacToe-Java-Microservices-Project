const port = 9002;
var jsonBody = require("body/json")
var sendJson = require("send-data/json")

require('http').createServer((req, res) => {

  function send(err, body){
	if(body.ping == "ping"){
		sendJson(req, res, {"pong":"pong"});
	} else {
		sendJson(req, res, {"message":"Can't pong that"});	
	}
	
  }
  jsonBody(req, res, send);
  
}).listen(port,() => {
  console.log(`Server running at ${port}`);
});