package cse551.tbd.gameserver.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cse551.tbd.gameserver.Application;
import cse551.tbd.gameserver.domain.Game;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class GameRepositoryTest {

    @Autowired
    GameRepository repository;

    @Test
    public void shouldFindGameById() throws Exception {
        Integer gameId = 0;
        Game game = Game.builder().gameId(gameId).build();

        game = this.repository.save(game);

        Game actual = this.repository.findByGameId(gameId);
        assertThat(actual, is(game));
    }

}
