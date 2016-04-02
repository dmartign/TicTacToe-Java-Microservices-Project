package cse551.tbd.gameserver.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    GameController gameController;
    @Mock
    GameService gameService;

    @Test
    public void shouldBeAbleToRetrieveGameState() throws Exception {
        Integer gameId = 0;
        Game expectedGame = Mockito.mock(Game.class);

        when(this.gameService.getGame(gameId)).thenReturn(expectedGame);

        ResponseEntity<Game> actual = this.gameController.getGameState(gameId);

        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody(), is(expectedGame));
    }
}
