package cse551.tbd.gameserver.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.gameserver.client.AuthenticationClient;
import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;
import cse551.tbd.gameserver.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private AuthenticationClient authClient;

    @RequestMapping(value = "/{gameId}", method = GET)
    public ResponseEntity<Game> getGameState(@PathVariable("gameId") String gameId) {
        Game game = this.gameService.getGame(gameId);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @RequestMapping(value = "/{gameId}", method = POST)
    public ResponseEntity<?> postGame(@CookieValue("userToken") String userToken, @PathVariable("gameId") String gameId, @RequestBody Game game) {
        User user = this.authClient.retrieveUser(userToken);
        game.setGameId(gameId);
        this.gameService.updateGame(user, game);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<String> createGame(@CookieValue("userToken") String userToken, @RequestBody User user2) {
        User user = this.authClient.retrieveUser(userToken);
        Game game = this.gameService.createGame(user, user2);
        return new ResponseEntity<String>(game.getGameId(), HttpStatus.OK);
    }
}
