package sk.tuke.gamestudio.logicalmazes.core;


public class Player extends Tile {
    private int row;
    private int column;

    public Player() {

    }

    public Player(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
