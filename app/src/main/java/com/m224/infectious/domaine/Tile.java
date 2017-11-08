package com.m224.infectious.domaine;

import com.m224.infectious.utils.State;

/**
 * Created by 224 on 2017-10-20.
 */

public class Tile {

    private State state;
    private boolean selected;
    private int id;

    public Tile(State state, int id) {
        this.state = state;
        this.id = id;
    }

    public boolean isStatePlayer() {
        if (this.state == State.PLAYER1 ||
                this.state == State.PLAYER2) {
            return true;
        }
        return false;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
