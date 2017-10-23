package com.m224.ataxx.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.m224.ataxx.domaine.Grid;
import com.m224.ataxx.interfaces.IGlobalVariable;

/**
 * Created by 224 on 2017-10-20.
 */

public class GridTileAdapter extends BaseAdapter {
    private Context context;
    private Grid grid;

    public GridTileAdapter(Context context, Grid grid) {
        this.context = context;
        this.grid = grid;
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
        return grid.getTileAt(position);
    }
}
























