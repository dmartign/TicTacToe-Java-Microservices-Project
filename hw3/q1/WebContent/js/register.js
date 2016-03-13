//Write a function that takes the form details and sends them to the localhost:8080/hw3q1/register endpoint as a POST
//The arguments needs to be sent as http parameters
//If the response is not "Success" need to
//http://stackoverflow.com/questions/9713058/sending-post-data-with-a-xmlhttprequest
function registerUser(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'register', true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.onload = function () {
		// do something to response
		console.log(this.responseText);
		document.getElementById('message').innerHTML = this.responseText;
		if(this.responseText === "Success"){
			window.location.href = 'login.html'
		}
	}


	function addParam(name){
		return name + '=' + document.getElementsByName(name)[0].value; 
	}

	function doRequest(){
		xhr.send(addParam('email') + '&' +
		addParam('password') + '&' +
		addParam('name') + '&' +
		addParam('streetaddress') + '&' +
		addParam('state') + '&' +
		addParam('zipcode'))
	}
	doRequest();
}