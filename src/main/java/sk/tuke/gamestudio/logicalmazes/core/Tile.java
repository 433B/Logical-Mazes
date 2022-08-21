package sk.tuke.gamestudio.logicalmazes.core;

import sk.tuke.gamestudio.logicalmazes.enumeration.TileState;

import java.io.Serializable;


public abstract class Tile implements Serializable {
    private TileState state = TileState.CLUE;

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }
}
