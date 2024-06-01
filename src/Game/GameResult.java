package Game;

public class GameResult {

    boolean isComplete;
    String winner;

    public GameResult(boolean isComplete, String winner) {
        this.isComplete = isComplete;
        this.winner = winner;
    }
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "isComplete=" + isComplete +
                ", winner='" + winner + '\'' +
                '}';
    }
}
