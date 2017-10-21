package com.m224.ataxx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by 224 on 2017-10-20.
 */

public class GridTileAdapter extends BaseAdapter {
    private Context context;
    private Tile[] tiles;

    public GridTileAdapter(Context context, Tile[] tiles) {
        this.context = context;
        this.tiles = tiles;
    }

    @Override
    public int getCount() {
        return tiles.length;
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
        return tiles[position];
    }
}
























