package com.m224.ataxx;

import android.content.Context;

/**
 * Created by 224 on 2017-10-20.
 */

public class Grid {

    private Tile[] tiles;


    public static Tile[] getConfig1(Context context) {
        Tile[] tiles = new Tile[IGlobalVariable.MAX_TILE];

        for (int i = 0; i < tiles.length; i++)
            tiles[i] = new Tile(context, IGlobalVariable.STATE.EMPTY);

        tiles[0].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[8].setState(IGlobalVariable.STATE.PLAYER2);
        tiles[72].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[80].setState(IGlobalVariable.STATE.PLAYER2);

        return tiles;
    }






}
