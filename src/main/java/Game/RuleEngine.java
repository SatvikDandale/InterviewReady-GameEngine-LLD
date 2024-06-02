package Game;

import board.TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

class Rule<T extends Board> {

    Function<T, GameState> condition;
    public Rule(Function<T, GameState> rule) {
        this.condition = rule;
    }

}

public class RuleEngine {

    Map<String, List<Rule<TicTacToe>>> rulesMap;

    public RuleEngine() {
        rulesMap = new HashMap<>();
        rulesMap.put(TicTacToe.class.getName(), new ArrayList<>());
        rulesMap.get(TicTacToe.class.getName()).add(new Rule<>(ticTacToeBoard -> completeTraversal(ticTacToeBoard::getSymbol)));
        rulesMap.get(TicTacToe.class.getName()).add(new Rule<>(ticTacToeBoard -> completeTraversal((i, j) -> ticTacToeBoard.getSymbol(j, i))));
        rulesMap.get(TicTacToe.class.getName()).add(new Rule<>(ticTacToeBoard -> innerTraversal(i -> ticTacToeBoard.getSymbol(i, i))));
        rulesMap.get(TicTacToe.class.getName()).add(new Rule<>(ticTacToeBoard -> innerTraversal(i -> ticTacToeBoard.getSymbol(i, 2 - i))));
        rulesMap.get(TicTacToe.class.getName()).add(new Rule<>(this::countMoves));
    }

    public GameState getGameState(Board board) {

        if (board instanceof TicTacToe) {
            TicTacToe ticTacToeBoard = (TicTacToe) board;
            List<Rule<TicTacToe>> rules = rulesMap.get(TicTacToe.class.getName());
            for (Rule<TicTacToe> rule : rules) {
                GameState gameState = rule.condition.apply(ticTacToeBoard);
                if (gameState.gameOver()) return gameState;
            }

            return new GameState(false, "-");
        }
        throw new IllegalArgumentException();
    }

    private GameState completeTraversal(BiFunction<Integer, Integer, String> characterSupplier) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            GameState traverse = innerTraversal(j -> characterSupplier.apply(finalI, j));
            if (traverse.gameOver()) return traverse;
        }
        return result;
    }

    private GameState innerTraversal(Function<Integer, String> characterSupplier) {
        GameState result = new GameState(false, "-");
        boolean win = true;
        for (int j = 0; j < 3; j++) {
            if (characterSupplier.apply(0) == null || !characterSupplier.apply(0).equals(characterSupplier.apply(j))) {
                win = false;
                break;
            }
        }
        if (win) result = new GameState(true, characterSupplier.apply(0));
        return result;
    }

    private GameState countMoves(TicTacToe board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) count++;
            }
        }
        if (count == 9) return new GameState(true, "-");
        return new GameState(false, "-");
    }


}
