package cse551.tbd.gameserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    private String gameId;
    private User player1;
    private User player2;
    private Character[][] board;

    {
        this.board = new Character[3][3];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Character(' ');
            }
        }
    }

}
