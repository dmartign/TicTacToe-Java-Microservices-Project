package cse551.tbd.gameserver.service;

import org.springframework.stereotype.Component;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.User;

@Component
public class GameMoveValidator {

    private static final boolean PLAYER1 = false;
    private static final boolean PLAYER2 = true;

    public boolean validate(User user, Game updatedGame, Game previousGame) {
        return this.smokeTest(updatedGame) && this.correctPlayer(user, previousGame);
    }

    protected boolean correctPlayer(User user, Game previousGame) {
        Character[][] board = previousGame.getBoard();
        boolean turn = PLAYER1;
        for (Character[] characters : board) {
            for (Character character : characters) {
                if (!character.equals(' ')) {
                    turn = !turn;
                }
            }
        }
        return (turn == PLAYER1 && user.getUsername().equals(previousGame.getPlayer1().getUsername()))
                || (turn == PLAYER2 && user.getUsername().equals(previousGame.getPlayer2().getUsername()));
    }

    protected boolean smokeTest(Game updatedGame) {
        Character[][] board = updatedGame.getBoard();
        boolean boardStatus = true;
        if (this.isValidBoard(board)) {
            for (Character[] row : board) {
                if (this.isValidRow(row) && boardStatus) {
                    for (Character character : row) {
                        if (!this.isValidSpace(character)) {
                            boardStatus = false;
                            break;
                        }
                    }
                } else {
                    boardStatus = false;
                    break;
                }
            }

        } else {
            boardStatus = false;
        }
        return boardStatus;
    }

    private boolean isValidSpace(Character character) {
        return character != null && (character.equals(' ') || character.equals('X') || character.equals('O'));
    }

    private boolean isValidRow(Character[] row) {
        return row != null && row.length == 3;
    }

    private boolean isValidBoard(Character[][] board) {
        return board != null && board.length == 3;
    }
}
