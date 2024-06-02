package Game;

public class GameInfo {

    private boolean gameOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private int numberOfMoves;

    public GameInfo(boolean gameOver, String winner, boolean hasFork, Player player, int numberOfMoves) {
        this.gameOver = gameOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.numberOfMoves = numberOfMoves;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isHasFork() {
        return hasFork;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
}
