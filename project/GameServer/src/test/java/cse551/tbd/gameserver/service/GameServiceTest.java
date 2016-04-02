package cse551.tbd.gameserver.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import cse551.tbd.gameserver.domain.Game;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    public void shouldBeAbleToGetAGame() throws Exception {
        Integer gameId = 0;
        Game expectedGame = Mockito.mock(Game.class);

        when(this.gameRepository.findByGameId(gameId)).thenReturn(expectedGame);

        Game actual = this.gameService.getGame(gameId);

        assertThat(actual, is(expectedGame));
    }
}
