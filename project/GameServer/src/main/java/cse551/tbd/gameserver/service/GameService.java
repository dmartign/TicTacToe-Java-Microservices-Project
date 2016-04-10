package cse551.tbd.gameserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.domain.Move;
import cse551.tbd.gameserver.domain.User;

@Component
public class GameService {

    private static final boolean PLAYER1 = false;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMoveValidator moveValidator;

    public Game getGame(String gameId) {

        return this.gameRepository.findByGameId(gameId);
    }

    public boolean updateGame(User user, Game game) {
        Game gameEntity = this.gameRepository.findByGameId(game.getGameId());
        if (this.moveValidator.validate(user, game, gameEntity)) {
            // TODO Check if it is the players turn
            // TODO Check if the board is valid, same size, only 1 difference
            // TODO Check victory conditions
            gameEntity.setBoard(game.getBoard());
            this.gameRepository.save(gameEntity);
            return true;
        } else {
            return false;
        }
    }

    public Game createGame(User user1, User user2) {
        Game game = new Game();
        game.setPlayer1(user1);
        game.setPlayer2(user2);
        this.gameRepository.save(game);
        return game;
    }

    public List<Game> getAllGame() {
        List<Game> games = Lists.newArrayList(this.gameRepository.findAll());
        return games;
    }

    public boolean updateGameMove(User user, String gameId, Move move) {
        Game gameEntity = this.gameRepository.findByGameId(gameId);
        boolean playerTurn = PLAYER1;
        if (this.gameOver(gameEntity)) {
            return false;
        }
        if (gameEntity.getBoard()[move.getRow()][move.getColumn()] != ' ') {
            return false;
        }
        for (Character[] row : gameEntity.getBoard()) {
            for (Character space : row) {
                if (space != ' ') {
                    playerTurn = !playerTurn;
                }
            }
        }
        if (playerTurn == PLAYER1 && gameEntity.getPlayer1().getUsername().equals(user.getUsername())) {
            gameEntity.getBoard()[move.getRow()][move.getColumn()] = 'X';
        } else if (gameEntity.getPlayer2().getUsername().equals(user.getUsername())) {
            gameEntity.getBoard()[move.getRow()][move.getColumn()] = 'O';
        } else {
            return false;
        }
        this.gameRepository.save(gameEntity);
        return true;
    }

    private class Point {
        int column;
        int row;
    }

    private Point cp(int c, int r) {
        Point point = new Point();
        point.column = c;
        point.row = r;
        return point;
    }

    private boolean gameOver(Game gameEntity) {

        Point[][] winningCombinations = { { this.cp(0, 0), this.cp(0, 1), this.cp(0, 2) }, { this.cp(1, 0), this.cp(1, 1), this.cp(1, 2) },
                { this.cp(2, 0), this.cp(2, 1), this.cp(2, 2) }, { this.cp(0, 0), this.cp(1, 0), this.cp(2, 0) },
                { this.cp(0, 1), this.cp(1, 1), this.cp(2, 1) }, { this.cp(0, 2), this.cp(1, 2), this.cp(2, 2) },
                { this.cp(0, 0), this.cp(1, 1), this.cp(2, 2) }, { this.cp(0, 2), this.cp(1, 1), this.cp(2, 0) }, };
        Character[][] board = gameEntity.getBoard();
        for (Point[] combination : winningCombinations) {
            if (board[combination[0].column][combination[0].row] != ' '
                    && board[combination[0].column][combination[0].row] == board[combination[1].column][combination[1].row]
                            && board[combination[0].column][combination[0].row] == board[combination[2].column][combination[2].row]) {
                return true;
            }
        }
        return false;
    }
}
