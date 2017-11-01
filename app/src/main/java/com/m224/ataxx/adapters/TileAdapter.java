package com.m224.ataxx.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.m224.ataxx.domaine.TileImageView;
import com.m224.ataxx.services.GameService;
import com.m224.ataxx.utils.IGlobalVariable;

import java.util.List;

/**
 * Created by 224 on 2017-10-20.
 */

public class TileAdapter extends BaseAdapter {
    private Context context;
    private List<TileImageView> gridImage;

    public TileAdapter(Context context, List<TileImageView> gridImage) {
        this.context = context;
        this.gridImage = gridImage;
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
        return gridImage.get(position);
    }
}
























