package Game;

public class GameState {

    boolean isComplete;
    String winner;

    public GameState(boolean isComplete, String winner) {
        this.isComplete = isComplete;
        this.winner = winner;
    }
    public boolean gameOver() {
        return isComplete;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "isComplete=" + isComplete +
                ", winner='" + winner + '\'' +
                '}';
    }
}
