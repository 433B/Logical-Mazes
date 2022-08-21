package sk.tuke.gamestudio.logicalmazes.core;

public class Coin extends Tile {
    private int count;

    public Coin() {
        this.count = 0;
    }

    public void countCoin() {
        count += 100;
    }

    public int getCount() {
        return count;
    }
}
