function abstractFormatter(data){
	div = document.getElementById('abstract');
	data = data.results.bindings[0];
	newHTML = data['abstract'].value;
	div.innerHTML = newHTML;
}

function pullAbstractData(city){
	var xhr = new XMLHttpRequest();
	params = "city="+city;
	url = 'abstract?' + params;
	console.log(url);
	xhr.open('GET', url, true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xhr.onload = function () {
		// do something to response
		console.log(this.responseText);
		
		abstractFormatter(JSON.parse(this.responseText));
	}
	xhr.send();
}

function readAbstract(){
	responsiveVoice.speak(document.getElementById('abstract').innerHTML);
}