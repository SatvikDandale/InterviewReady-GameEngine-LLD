package api;

import Game.*;
import board.TicTacToe;

public class GameEngine {

    public Board start(String type) {

        if (type.equals("TicTacToe")) {
            return new TicTacToe();
        } else throw new IllegalArgumentException();

    }

    public void move(Board board, Player player, Move move) {

        if (board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            board1.setCell(move.getCell(), player.getSymbol());

        } else {
            throw new IllegalArgumentException();
        }

    }

    public GameResult isComplete(Board board) {

        if (board instanceof TicTacToe) {

            // Row Complete Check
            TicTacToe board1 = (TicTacToe) board;
            boolean rowComplete = true;
            String firstCharacter = "-";
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(i, 0);
                rowComplete = firstCharacter != null;
                if (firstCharacter != null)
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                if (rowComplete) break;
            }
            if (rowComplete) return new GameResult(true, firstCharacter);
            // Col Complete Check
            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(0, i);
                colComplete = firstCharacter != null;
                if (firstCharacter != null)
                    for (int j = 0; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                if (colComplete) break;
            }
            if (colComplete) return new GameResult(true, firstCharacter);
            // Diag complete check
            firstCharacter = board1.getCell(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(i, i))) {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete) return new GameResult(true, firstCharacter);
            // Rev Diag complete check
            firstCharacter = board1.getCell(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(i, 2 - i))) {
                    revDiagComplete = false;
                    break;
                }
            }
            if (revDiagComplete) return new GameResult(true, firstCharacter);

            // Check places filled
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) != null) count++;
                }
            }
            if (count == 9) return new GameResult(true, "-");
            return new GameResult(false, "-");
        }
        return new GameResult(false, "-");
    }

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) return new Move(new Cell(i, j));
                }
            }
            throw new IllegalStateException();
        } else throw new IllegalArgumentException();
    }


}