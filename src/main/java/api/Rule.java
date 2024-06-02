package api;

import Game.Board;
import Game.GameState;

import java.util.function.Function;

public class Rule<T extends Board> {

    Function<T, GameState> condition;

    public Rule(Function<T, GameState> rule) {
        this.condition = rule;
    }

}
