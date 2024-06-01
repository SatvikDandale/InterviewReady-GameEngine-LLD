package Game;

import board.TicTacToe;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getGameState(Board board) {

        if (board instanceof TicTacToe) {
            TicTacToe ticTacToeBoard = (TicTacToe) board;

            GameState rowWin = isVictory((i, j) -> ticTacToeBoard.getSymbol(i, j));
            if (rowWin != null) return rowWin;

            GameState colWin = isVictory((i, j) -> ticTacToeBoard.getSymbol(j, i));
            if (colWin != null) return colWin;

            GameState diagVictory = isDiagVictory(i -> ticTacToeBoard.getSymbol(i, i));
            if (diagVictory != null) return diagVictory;

            GameState revDiagVictory = isDiagVictory(i -> ticTacToeBoard.getSymbol(i, 2 - i));
            if (revDiagVictory != null) return revDiagVictory;


            // Check places filled
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getSymbol(i, j) != null) count++;
                }
            }
            if (count == 9) return new GameState(true, "-");
            return new GameState(false, "-");
        }
        return new GameState(false, "-");
    }

    private GameState isDiagVictory(Function<Integer, String> characterSupplier) {
        boolean diagWin = true;
        for (int i = 0; i < 3; i++) {
            if (characterSupplier.apply(0) == null || !characterSupplier.apply(0).equals(characterSupplier.apply(i))) {
                diagWin = false;
                break;
            }
        }
        if (diagWin) return new GameState(true, characterSupplier.apply(0));
        return null;
    }

    private static GameState isVictory(BiFunction<Integer, Integer, String> characterSupplier) {
        for (int i = 0; i < 3; i++) {
            boolean victory = true;
            for (int j = 0; j < 3; j++) {
                if (characterSupplier.apply(i, 0) == null ||
                        characterSupplier.apply(i, j) == null ||
                        !characterSupplier.apply(i, 0).equals(characterSupplier.apply(i, j))) {
                    victory = false;
                    break;
                }
            }
            if (victory) return new GameState(true, characterSupplier.apply(i, 0));
        }
        return null;
    }

}
