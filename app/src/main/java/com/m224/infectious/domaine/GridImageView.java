package com.m224.infectious.domaine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.m224.infectious.R;
import com.m224.infectious.utils.State;

/**
 * Created by 224 on 2017-11-01.
 */

public class GridImageView extends ImageView {

    private State state;
    private boolean selected;
    private int id;

    public GridImageView(Context context, State state, int id) {
        super(context);
        this.state = state;
        this.id = id;
        changeImageResource();
    }

    public void setState(State state) {
        this.state = state;
        changeImageResource();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        changeImageResource();
    }

    private void changeImageResource() {
        Drawable[] drawables = new Drawable[2];

        if (state == State.EMPTY) {
            drawables[0] = getResources().getDrawable(R.drawable.tile_empty);
        } else if (state == State.PLAYER1) {
            drawables[0] = getResources().getDrawable(R.drawable.tile_player1);
        } else if (state == State.PLAYER2) {
            drawables[0] = getResources().getDrawable(R.drawable.tile_player2);
        } else {
            drawables[0] = getResources().getDrawable(R.drawable.tile_block);
        }

        if (selected) {
            drawables[1] = getResources().getDrawable(R.drawable.tile_selected);
        } else {
            drawables[1] = getResources().getDrawable(R.drawable.tile_not_selected);
        }

        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        this.setImageDrawable(layerDrawable);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
