package board;

import Game.Board;
import Game.Cell;
import Game.Move;

public class TicTacToe implements Board {

    String cells[][] = new String[3][3];

    public enum Symbol {
        X("X"), O("O");

        final String symbol;
        Symbol(String symbol) {
            this.symbol = symbol;
        }
        public String getSymbol() {
            return symbol;
        }
    }

    // getCell is defined in TicTacToe and not in board
    // because the parent Board might not have cells
    // but TicTacToe definitely does
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] != null)
            throw new IllegalArgumentException();
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

    @Override
    public TicTacToe copy() {
        TicTacToe copy = new TicTacToe();
        for(int i=0; i < 3; i++) {
            // Deep copying strings row wise using arraycopy
            System.arraycopy(cells[i], 0, copy.cells[i], 0, 0);
        }
        return copy;
    }

}
