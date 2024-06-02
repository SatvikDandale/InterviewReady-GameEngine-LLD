package api;

import Game.*;
import board.TicTacToe;
import board.TicTacToe.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    public GameInfo getGameInfo(Board board) {
        if (board instanceof TicTacToe) {
            TicTacToe ticTacToeBoard = (TicTacToe) board;
            GameState gameState = getGameState(ticTacToeBoard);
            for(Symbol symbol: Symbol.values()) {
                for(int i=0; i < 3; i++) {
                    for(int j=0; j < 3; j++) {
                        Player player = new Player(symbol.getSymbol());
                        if (ticTacToeBoard.getSymbol(i, j) != null) {
                            TicTacToe b = ticTacToeBoard.copy();
                            b.move(new Move(new Cell(i, j), player));
                            boolean canStillWin = false;
                            for(int k=0; k < 3; k++) {
                                for(int l=0; l < 3; l++) {
                                    Board b1 = b.copy();
                                    b1.move(new Move(new Cell(k, l), player.flip()));
                                    gameState = getGameState(b1);
                                    if (gameState.getWinner().equals(player.flip().getSymbol())) {
                                        canStillWin = true;
                                        break;
                                    }
                                }
                            }
                            if (canStillWin)
                                return new GameInfoBuilder()
                                        .setHasFork(gameState.gameOver())
                                        .setWinner(gameState.getWinner())
                                        .setHasFork(true)
                                        .setPlayer(player.flip())
                                        .build();

                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .setGameOver(gameState.gameOver())
                    .setWinner(gameState.getWinner())
                    .build();
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
