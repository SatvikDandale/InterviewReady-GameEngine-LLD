package Game;

import board.TicTacToe;

public class AIEngine {

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            int threshold = 4;
            TicTacToe copy = board1.copy();
            // Facade design patter: Abstracted the algorithm by a single function call
            if (countMoves(copy) < threshold) {
               return playBasicMove(copy, player);
            }
            return playSmartMove(copy, player);
        } else throw new IllegalArgumentException();
    }

    private Move playBasicMove(TicTacToe board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) return new Move(new Cell(i, j), player);
            }
        }
        throw new IllegalStateException();
    }

    private Move playSmartMove(TicTacToe board, Player player) {
        RuleEngine ruleEngine = new RuleEngine();
        // Attack
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    board.move(move);
                    if (ruleEngine.getGameState(board).gameOver()) {
                        return move;
                    }
                }
            }
        }
        // Defend
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    // If other player makes this move and wins, we should play that move
                    board.move(new Move(new Cell(i, j), player.flip()));
                    if (ruleEngine.getGameState(board).gameOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }
        // If both attack and defend moves are not available
        return playBasicMove(board, player);
    }

    private int countMoves(Board board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
            }
        }
        return count;
    }

}
