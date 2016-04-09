function createGame(){
	var challengedInput = document.getElementById("challengeinput");
	user = {
		username:challengedInput.value
	}
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:9002/game/game', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.withCredentials = true;
	xhr.onreadystatechange = function (oEvent) {  
    if (xhr.readyState === 4) {  
        if (xhr.status === 200) {  
          console.log(xhr.responseText)  
        } else {  
           console.log("Error", xhr.statusText);
           console.log(xhr.responseText)  
        }  
    }};
	xhr.send(JSON.stringify(user));
}

function populateGames(games){
	var gamelist = document.getElementById("gamelist");
	gamelist.innerHTML = "";
	games.forEach(function(game){
		gamelist.innerHTML += "<tr><td>"+game.player1.username + " vs " + game.player2.username + "</td><td><input type='button' value='Join' onclick='joinGame(\""+game.gameId+"\")'></td></tr>"
	});
}

function getData(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:9002/game/game', true);
	xhr.onload = function () {
		var games = JSON.parse(this.responseText);
		populateGames(games);
	}
	xhr.send();
}

window.setInterval(getData,5000);
getData();