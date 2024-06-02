package Game;

public class GameInfoBuilder {
    private boolean gameOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private int numberOfMoves;

    public GameInfoBuilder setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }

    public GameInfoBuilder setWinner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder setHasFork(boolean hasFork) {
        this.hasFork = hasFork;
        return this;
    }

    public GameInfoBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public GameInfoBuilder setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
        return this;
    }
    public GameInfo build() {
        return new GameInfo(gameOver, winner, hasFork, player, numberOfMoves);
    }
}
