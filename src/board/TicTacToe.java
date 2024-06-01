package board;

import Game.Board;
import Game.Cell;
import Game.Move;
import Game.Player;

public class TicTacToe extends Board {

    String cells[][] = new String[3][3];

    // getCell is defined in TicTacToe and not in board
    // because the parent Board might not have cells
    // but TicTacToe definitely does
    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i < 3; i++) {
            for(int j=0; j < 3; j++) {
                result.append(cells[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());
    }

}
