package cse551.tbd.gameserver.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;
import cse551.tbd.gameserver.service.GameRepository;

@Configuration
public class GameConfiguration implements InitializingBean {

    private static final User PLAYER1 = User.builder().username("Player1").build();
    private static final User PLAYER2 = User.builder().username("Player2").build();

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.createGame("0", PLAYER1, PLAYER2);
    }

    private void createGame(String gameId, User player1, User player2) {
        Game game = new Game();
        game.setGameId(gameId);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        this.gameRepository.save(game);
    }

}
