package com.m224.infectious.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.m224.infectious.domaine.GridImageView;
import com.m224.infectious.utils.GridConfig;

import java.util.List;

/**
 * Created by 224 on 2017-10-20.
 */

public class GridImageAdapter extends BaseAdapter {
    private Context context;
    private List<GridImageView> gridImage;

    public GridImageAdapter(Context context, List<GridImageView> gridImage) {
        this.context = context;
        this.gridImage = gridImage;
    }

    @Override
    public int getCount() {
        return GridConfig.MAX_TILE;
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
        return gridImage.get(position);
    }
}
























