package com.m224.ataxx;

import android.content.Context;

/**
 * Created by 224 on 2017-10-20.
 */

public class Grid {

    private Tile[] tiles;
    private Context context;

    public Grid(Context context) {
        this.tiles = new Tile[IGlobalVariable.MAX_TILE];
        this.context = context;

        for (int i = 0; i < tiles.length; i++)
            tiles[i] = new Tile(context, IGlobalVariable.STATE.EMPTY);
    }

    public Tile getTileAt(int i) {
        return tiles[i];
    }

    private void resetTile() {
        for (int i = 0; i < tiles.length; i++)
            tiles[i].setState(IGlobalVariable.STATE.EMPTY);
    }

    public void setConfig1() {
        resetTile();
        tiles[0].setState(IGlobalVariable.STATE.PLAYER2);
        tiles[8].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[72].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[80].setState(IGlobalVariable.STATE.PLAYER2);
    }




}
