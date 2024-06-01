package Game;

import board.TicTacToe;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getGameState(Board board) {

        if (board instanceof TicTacToe) {

            TicTacToe board1 = (TicTacToe) board;

            BiFunction<Integer, Integer, String> getSymbolForRowCheck = board1::getSymbol;
            BiFunction<Integer, Integer, String> getSymbolForColCheck = (i, j) -> board1.getSymbol(j, i);

            GameState rowWin = isVictory(getSymbolForRowCheck);
            if (rowWin != null) return rowWin;

            GameState colWin = isVictory(getSymbolForColCheck);
            if (colWin != null) return colWin;


            String firstCharacter = "-";
            // Diag complete check
            firstCharacter = board1.getSymbol(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getSymbol(i, i))) {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete) return new GameState(true, firstCharacter);
            // Rev Diag complete check
            firstCharacter = board1.getSymbol(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getSymbol(i, 2 - i))) {
                    revDiagComplete = false;
                    break;
                }
            }
            if (revDiagComplete) return new GameState(true, firstCharacter);

            // Check places filled
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) count++;
                }
            }
            if (count == 9) return new GameState(true, "-");
            return new GameState(false, "-");
        }
        return new GameState(false, "-");
    }

    private static GameState isVictory(BiFunction<Integer, Integer, String> getNext) {
        for (int i = 0; i < 3; i++) {
            boolean victory = true;
            for (int j = 0; j < 3; j++) {
                if (getNext.apply(i, 0) == null ||
                        getNext.apply(i, j) == null ||
                        !getNext.apply(i, 0).equals(getNext.apply(i, j))) {
                    victory = false;
                    break;
                }
            }
            if (victory) return new GameState(true, getNext.apply(i, 0));
        }
        return null;
    }

}
