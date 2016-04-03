package cse551.tbd.gameserver.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    public void shouldBeAbleToGetAGame() throws Exception {
        String gameId = "0";
        Game expectedGame = Mockito.mock(Game.class);

        when(this.gameRepository.findByGameId(gameId)).thenReturn(expectedGame);

        Game actual = this.gameService.getGame(gameId);

        assertThat(actual, is(expectedGame));
    }

    @Test
    public void shouldBeAbleToUpdateOnlyBoardOfGame() throws Exception {
        User user = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Game game = new Game();
        String gameId = "0";
        game.setGameId(gameId);

        Game gameEntity = new Game();
        gameEntity.setGameId(gameId);
        gameEntity.setPlayer1(user);
        gameEntity.setPlayer2(user2);
        Character[][] oldBoard = gameEntity.getBoard();
        when(this.gameRepository.findByGameId(game.getGameId())).thenReturn(gameEntity);

        boolean actual = this.gameService.updateGame(user, game);

        verify(this.gameRepository).save(gameEntity);
        assertThat(gameEntity.getPlayer1(), is(user));
        assertThat(gameEntity.getPlayer2(), is(user2));
        assertThat(gameEntity.getBoard(), is(game.getBoard()));
        assertThat(gameEntity.getBoard(), not(sameInstance(oldBoard)));
        assertThat(actual, is(true));
    }

    @Test
    public void canCreateGame() throws Exception {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);

        Game actualGame = this.gameService.createGame(user1, user2);

        verify(this.gameRepository).save(actualGame);
        assertThat(actualGame.getPlayer1(), is(user1));
        assertThat(actualGame.getPlayer2(), is(user2));
    }
}
