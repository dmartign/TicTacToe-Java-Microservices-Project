package cse551.tbd.gameserver.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/{gameId}", method = GET)
    public ResponseEntity<Game> getGameState(@PathVariable("gameId") Integer gameId) {
        Game game = this.gameService.getGame(gameId);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

}
