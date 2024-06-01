package api;

import Game.*;
import board.TicTacToe;

public class GameEngine {

    public Board start(String type) {

        if (type.equals("TicTacToe")) {
            return new TicTacToe();
        } else throw new IllegalArgumentException();

    }

    public void move(Board board, Move move) {
        if (!(board instanceof TicTacToe))
            throw new IllegalArgumentException();
        board.move(move);
    }






}