import Game.*;
import api.GameEngine;
import api.RuleEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }


    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 1}, {1, 1}, {2, 1}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {2, 2}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {2, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {2, 1}, {2, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        while (next < firstPlayerMoves.length && !ruleEngine.getGameState(board).gameOver()) {
            Player human = new Player("X"), computer = new Player("O");
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);
            if (!ruleEngine.getGameState(board).gameOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move computerMove = new Move(new Cell(sRow, sCol), computer);
                gameEngine.move(board, computerMove);
            }
            next++;
        }
    }

}
