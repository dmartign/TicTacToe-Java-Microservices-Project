//Write a function that takes the form details and sends them to the localhost:8080/hw3q1/register endpoint as a POST
//The arguments needs to be sent as http parameters
//If the response is not "Success" need to
//http://stackoverflow.com/questions/9713058/sending-post-data-with-a-xmlhttprequest
function registerUser(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:9001/authentication/user', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.withCredentials = true;
	xhr.onload = function () {
		// do something to response
		console.log(this.responseText);
		document.getElementById('message').innerHTML = this.responseText;
		if(this.responseText === "true"){
			window.location.href = 'login.html'
		}
	}


	function addParam(name){
		return name + '=' + document.getElementsByName(name)[0].value; 
	}

	function doRequest(){
		user = {
				email:document.getElementById('email').value,
				password:document.getElementById('password').value,
				username:document.getElementById('username').value
		}
		xhr.send(JSON.stringify(user));
	}
	doRequest();
}