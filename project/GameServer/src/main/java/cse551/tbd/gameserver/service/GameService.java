package cse551.tbd.gameserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cse551.tbd.gameserver.domain.Game;

@Component
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGame(Integer gameId) {

        return this.gameRepository.findByGameId(gameId);
    }

}
