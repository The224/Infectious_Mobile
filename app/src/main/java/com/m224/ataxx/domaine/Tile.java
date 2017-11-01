package com.m224.ataxx.domaine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.m224.ataxx.R;
import com.m224.ataxx.utils.IGlobalVariable;

/**
 * Created by 224 on 2017-10-20.
 */

public class Tile {

    private IGlobalVariable.STATE state;
    private boolean selected;
    private int id;

    public Tile(IGlobalVariable.STATE state, int id) {
        this.state = state;
        this.id = id;
    }

    public boolean isStatePlayer() {
        if (this.state == IGlobalVariable.STATE.PLAYER1 ||
                this.state == IGlobalVariable.STATE.PLAYER2) {
            return true;
        }
        return false;
    }

    public IGlobalVariable.STATE getState() {
        return state;
    }

    public void setState(IGlobalVariable.STATE state) {
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
