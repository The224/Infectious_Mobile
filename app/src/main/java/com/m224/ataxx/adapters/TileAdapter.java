package com.m224.ataxx.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.m224.ataxx.controllers.GameController;
import com.m224.ataxx.interfaces.IGlobalVariable;

/**
 * Created by 224 on 2017-10-20.
 */

public class TileAdapter extends BaseAdapter {
    private Context context;
    private GameController gameController;

    public TileAdapter(Context context, GameController gameController) {
        this.context = context;
        this.gameController = gameController;
    }

    @Override
    public int getCount() {
        return IGlobalVariable.MAX_TILE;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        return gameController.getTileAt(position);
    }
}
























