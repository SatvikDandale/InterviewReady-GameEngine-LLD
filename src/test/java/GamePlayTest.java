import Game.*;
import api.GameEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    AIEngine aiEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
    }

    private void playGame(Board board, int[][] moves) {
        int next = 0;
        while (!ruleEngine.getGameState(board).gameOver()) {
            Player human = new Player("X"), computer = new Player("O");
            int row = moves[next][0];
            int col = moves[next][1];
            next++;
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);
            if (!ruleEngine.getGameState(board).gameOver()) {
                Move computerMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computerMove);
            }
        }
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int next = 0;
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0, 1}, {1, 1}, {2, 1}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getGameState(board).gameOver());
        Assert.assertEquals(ruleEngine.getGameState(board).getWinner(), "O");
    }

}
