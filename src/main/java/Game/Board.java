package Game;

public interface Board {

    void move(Move move);
    Board copy();   // Prototype Design pattern for cloning using objects

}
