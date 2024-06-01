package Game;

public class Move {

    Player player;

    Cell cell;

    public Move (Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getCell() {
        return cell;
    }

}
