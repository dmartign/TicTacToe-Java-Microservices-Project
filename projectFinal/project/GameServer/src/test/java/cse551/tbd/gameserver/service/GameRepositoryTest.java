package cse551.tbd.gameserver.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cse551.tbd.gameserver.GameServerApplication;
import cse551.tbd.gameserver.domain.Game;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GameServerApplication.class)
public class GameRepositoryTest {

    @Autowired
    GameRepository repository;

    @Test
    public void shouldFindGameById() throws Exception {
        String gameId = "0";
        Game game = new Game();
        game.setGameId(gameId);

        game = this.repository.save(game);

        Game actual = this.repository.findByGameId(gameId);
        assertThat(actual, is(game));
    }

}
