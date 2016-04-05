package cse551.tbd.gameserver.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;

public class GameMoveValidatorTest {

    private static final User PLAYER1 = User.builder().username("PLAYER1").build();
    private static final User PLAYER2 = User.builder().username("PLAYER2").build();
    private Game game;
    GameMoveValidator validator = new GameMoveValidator();

    @Before
    public void before() {
        this.game = new Game();
        this.game.setPlayer1(PLAYER1);
        this.game.setPlayer2(PLAYER2);
    }

    @Test
    public void player1sTurn() throws Exception {
        this.game.getBoard()[0][0] = 'X';
        this.game.getBoard()[0][1] = 'O';

        boolean actual = this.validator.correctPlayer(PLAYER1, this.game);

        assertThat(actual, is(true));
    }

    @Test
    public void player2sTurn() throws Exception {
        this.game.getBoard()[0][0] = 'X';

        boolean actual = this.validator.correctPlayer(PLAYER2, this.game);

        assertThat(actual, is(true));
    }

    @Test
    public void smokeTestValidBoard() throws Exception {
        this.game.getBoard()[0][0] = 'X';
        this.game.getBoard()[0][1] = 'O';

        boolean actual = this.validator.smokeTest(this.game);

        assertThat(actual, is(true));
    }

    @Test
    public void smokeTestInvalidSize() throws Exception {
        this.game.getBoard()[0] = new Character[4];

        boolean actual = this.validator.smokeTest(this.game);

        assertThat(actual, is(false));
    }

    @Test
    public void smokeTestInvalidCharacter() throws Exception {
        this.game.getBoard()[0][0] = 'Z';
        this.game.getBoard()[0][1] = 'Y';

        boolean actual = this.validator.smokeTest(this.game);

        assertThat(actual, is(false));
    }

    @Test
    public void smokeTestNull() throws Exception {
        this.game.getBoard()[2][2] = null;

        boolean actual = this.validator.smokeTest(this.game);

        assertThat(actual, is(false));
    }

}
