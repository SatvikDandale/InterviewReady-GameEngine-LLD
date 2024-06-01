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

            GameState diagVictory = traversal(i -> ticTacToeBoard.getSymbol(i, i));
            if (diagVictory != null) return diagVictory;

            GameState revDiagVictory = traversal(i -> ticTacToeBoard.getSymbol(i, 2 - i));
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

    private GameState isVictory(BiFunction<Integer, Integer, String> characterSupplier) {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            GameState traverse = traversal(j -> characterSupplier.apply(finalI, j));
            if (traverse != null) return traverse;
        }
        return null;
    }

    private GameState traversal(Function<Integer, String> characterSupplier) {
        boolean win = true;
        for (int j = 0; j < 3; j++) {
            if (characterSupplier.apply(0) == null || !characterSupplier.apply(0).equals(characterSupplier.apply(j))) {
                win = false;
                break;
            }
        }
        if (win) return new GameState(true, characterSupplier.apply(0));
        return null;
    }


}
