import Game.Board;
import Game.Cell;
import Game.Move;
import Game.Player;
import api.GameEngine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");

        Scanner scanner = new Scanner(System.in);
        while(!gameEngine.isComplete(board).isComplete()) {
            Player human = new Player("X"), computer = new Player("O");
            System.out.println(board);
            System.out.println("Make your move!");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move humanMove = new Move(new Cell(row, col));
            gameEngine.move(board, human, humanMove);
            System.out.println(board);
            if (!gameEngine.isComplete(board).isComplete()) {
                Move computerMove = gameEngine.suggestMove(board, computer);
                gameEngine.move(board, computer, computerMove);
            }
        }
        System.out.println("GameResult: " + gameEngine.isComplete(board));

    }

}
