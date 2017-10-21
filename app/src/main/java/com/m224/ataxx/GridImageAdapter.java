package com.m224.ataxx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by 224 on 2017-10-20.
 */

public class GridImageAdapter extends BaseAdapter {
    private Context context;

    public GridImageAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return 81;
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
        Tile imageView;
        if (view == null) { // Eco-Responsable
            imageView = new Tile(context);
        } else {
            imageView = (Tile) view;
        }

        imageView.setImageResource(R.drawable.tile_block);
        return imageView;
    }
}
























