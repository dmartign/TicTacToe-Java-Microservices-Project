package cse551.tbd.gameserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;

@Component
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGame(String gameId) {

        return this.gameRepository.findByGameId(gameId);
    }

    public boolean updateGame(User user, Game game) {
        Game gameEntity = this.gameRepository.findByGameId(game.getGameId());
        // TODO Check if it is the players turn
        // TODO Check if the board is valid, same size, only 1 difference
        // TODO Check victory conditions
        gameEntity.setBoard(game.getBoard());
        this.gameRepository.save(gameEntity);
        return true;
    }

    public Game createGame(User user1, User user2) {
        Game game = new Game();
        game.setPlayer1(user1);
        game.setPlayer2(user2);
        this.gameRepository.save(game);
        return game;
    }

    public List<Game> getAllGame() {
        List<Game> games = Lists.newArrayList(this.gameRepository.findAll());
        return games;
    }

}
