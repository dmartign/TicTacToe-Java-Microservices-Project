package cse551.tbd.gameserver.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.gameserver.client.AuthenticationClient;
import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;
import cse551.tbd.gameserver.service.GameService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping(value = "/game")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private AuthenticationClient authClient;

    @RequestMapping(method = GET)
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = this.gameService.getAllGame();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

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
        if (user == null) {
            throw new IllegalArgumentException("No User");
        }
        Game game = this.gameService.createGame(user, user2);
        return new ResponseEntity<String>(game.getGameId(), HttpStatus.OK);
    }
}
