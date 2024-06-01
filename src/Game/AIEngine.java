package Game;

import board.TicTacToe;

public class AIEngine {

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) return new Move(new Cell(i, j), player);
                }
            }
            throw new IllegalStateException();
        } else throw new IllegalArgumentException();
    }

}
