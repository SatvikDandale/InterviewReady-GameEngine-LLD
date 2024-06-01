package Game;

public class Player {

    String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Player flip() {
        return new Player("X".equals(symbol) ? "O" : "X");
    }

}
