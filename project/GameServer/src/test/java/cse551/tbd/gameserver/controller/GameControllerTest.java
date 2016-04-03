package cse551.tbd.gameserver.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cse551.tbd.gameserver.client.AuthenticationClient;
import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;
import cse551.tbd.gameserver.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    GameController gameController;
    @Mock
    GameService gameService;
    @Mock
    AuthenticationClient authClient;

    @Test
    public void shouldBeAbleToRetrieveGameState() throws Exception {
        String gameId = "0";
        Game expectedGame = Mockito.mock(Game.class);

        when(this.gameService.getGame(gameId)).thenReturn(expectedGame);

        ResponseEntity<Game> actual = this.gameController.getGameState(gameId);

        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody(), is(expectedGame));
    }

    @Test
    public void canPostGameState() throws Exception {
        String gameId = "0";
        Game game = Mockito.mock(Game.class);
        User user = Mockito.mock(User.class);
        String userToken = "token";

        when(this.authClient.retrieveUser(userToken)).thenReturn(user);
        when(this.gameService.updateGame(user, game)).thenReturn(true);

        ResponseEntity<?> actual = this.gameController.postGame(userToken, gameId, game);

        verify(game).setGameId(gameId);
        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void canCreateGame() throws Exception {
        String userToken = "token";
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Game expected = new Game();
        String gameId = "0";
        expected.setGameId(gameId);

        when(this.authClient.retrieveUser(userToken)).thenReturn(user1);
        when(this.gameService.createGame(user1, user2)).thenReturn(expected);

        ResponseEntity<String> actual = this.gameController.createGame(userToken, user2);

        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody(), is(expected.getGameId()));
    }
}
