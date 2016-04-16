

function formatter(div, data){
	div = document.getElementById(div);
	fields = data.head.vars;
	data = data.results.bindings;
	newHTML = '<table><tr>';
	fields.forEach(function(currentValue){
		newHTML += '<th>'+currentValue+'</th>'
		
	});
	newHTML += '</tr>';
	data.forEach(function(currentValue){
		newHTML += '<tr>'
		fields.forEach(function(currentField){
			newHTML += '<td>'+currentValue[currentField].value+'</td>'
			
		})
		newHTML += '</tr>' 
		
		
	});
	newHTML += '</table>'
	div.innerHTML = newHTML;
}

function pullData(query, city){
	var xhr = new XMLHttpRequest();
	params = "city="+city;
	url = query+'?' + params;
	console.log(url);
	xhr.open('GET', url, true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xhr.onload = function () {
		// do something to response
		console.log(this.responseText);
		
		formatter(query, JSON.parse(this.responseText));
	}
	xhr.send();
}