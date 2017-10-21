package com.m224.ataxx;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by 224 on 2017-10-20.
 */

public class Tile extends ImageView {

    private IGlobalVariable.STATE state;

    public void setState(IGlobalVariable.STATE state) {
        this.state = state;
        changeImageResource();
    }

    public IGlobalVariable.STATE getState() {
        return state;
    }

    public Tile(Context context, IGlobalVariable.STATE state) {
        super(context);
        this.state = state;
        changeImageResource();
    }

    private void changeImageResource() {
        if (state == IGlobalVariable.STATE.EMPTY) {
            this.setImageResource(R.drawable.tile_empty);
        } else if (state == IGlobalVariable.STATE.PLAYER1) {
            this.setImageResource(R.drawable.tile_player1);
        } else if (state == IGlobalVariable.STATE.PLAYER2) {
            this.setImageResource(R.drawable.tile_player2);
        } else {
            this.setImageResource(R.drawable.tile_block);
        }
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
