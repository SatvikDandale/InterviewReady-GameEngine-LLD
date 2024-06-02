import Game.*;
import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();

        Scanner scanner = new Scanner(System.in);
        while (!ruleEngine.getGameState(board).gameOver()) {
            Player human = new Player("X"), computer = new Player("O");
            System.out.println(board);
            System.out.println("Make your move!");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);
            System.out.println(board);
            if (!ruleEngine.getGameState(board).gameOver()) {
                Move computerMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computerMove);
            }
        }
        System.out.println("GameResult: " + ruleEngine.getGameState(board));

    }

}
