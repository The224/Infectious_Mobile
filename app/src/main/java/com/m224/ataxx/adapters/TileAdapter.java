package com.m224.ataxx.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.m224.ataxx.services.GameService;
import com.m224.ataxx.utils.IGlobalVariable;

/**
 * Created by 224 on 2017-10-20.
 */

public class TileAdapter extends BaseAdapter {
    private Context context;
    private GameService gameService;

    public TileAdapter(Context context, GameService gameService) {
        this.context = context;
        this.gameService = gameService;
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
        return gameService.getTileAt(position);
    }
}
























