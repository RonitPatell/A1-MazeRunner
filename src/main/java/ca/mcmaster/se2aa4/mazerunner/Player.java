package ca.mcmaster.se2aa4.mazerunner;

public class Player {

    private int x;
    private int y;

    public Player(int startRow) {
        this.x = startRow;
        this.y = 0;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public void advance() {
        y++;
    }
}
