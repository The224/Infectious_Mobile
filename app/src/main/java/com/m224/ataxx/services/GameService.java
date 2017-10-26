package com.m224.ataxx.services;

import android.content.Context;

import com.m224.ataxx.domaine.Tile;
import com.m224.ataxx.utils.IGlobalVariable;

/**
 * Created by 224 on 2017-10-23.
 */

public class GameService {

    private Tile[] tiles;
    private Context context;
    private int selectTile = IGlobalVariable.DEFAULT_SELECTED_TILE;

    private int scorePlayer1;
    private int scorePlayer2;

    public GameService(Context context) {
        this.tiles = new Tile[IGlobalVariable.MAX_TILE];
        this.context = context;

        for (int i = 0; i < tiles.length; i++)
            tiles[i] = new Tile(context, IGlobalVariable.STATE.EMPTY);

        tiles[selectTile].setSelected(true);

    }

    public Tile getTileAt(int i) {
        return tiles[i];
    }

    private void resetTile() {
        for (int i = 0; i < tiles.length; i++)
            tiles[i].setState(IGlobalVariable.STATE.EMPTY);
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    private void countScore() {
        scorePlayer1=0;
        scorePlayer2=0;
        for (Tile tile : tiles) {
            if (tile.getState() == IGlobalVariable.STATE.PLAYER1)
                scorePlayer1++;
            if (tile.getState() == IGlobalVariable.STATE.PLAYER2)
                scorePlayer2++;
        }
    }

    public void setConfig1() {
        resetTile();
        tiles[0].setState(IGlobalVariable.STATE.PLAYER2);
        tiles[8].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[72].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[80].setState(IGlobalVariable.STATE.PLAYER2);
        countScore();
    }

    public void changeSelectedTile(int newSelected) {
        if (tiles[newSelected].getState() == IGlobalVariable.STATE.PLAYER1 ||
                tiles[newSelected].getState() == IGlobalVariable.STATE.PLAYER2) {
            if (selectTile >= 0)
                tiles[selectTile].setSelected(false);
            tiles[newSelected].setSelected(true);
            selectTile = newSelected;
        }
    }

    public void unSelectTile(){
        tiles[selectTile].setSelected(false);
    }

    public void makeMove(int tileId) {
        Tile moveTile = tiles[tileId];
        // Prevent selectTile to be null
        if (selectTile < 0) {
            changeSelectedTile(tileId);
            return;
        }

        if (moveTile.getState() == IGlobalVariable.STATE.EMPTY) {
            moveTile.setState(tiles[selectTile].getState());
            //tiles[selectTile].setState(IGlobalVariable.STATE.EMPTY);
            unSelectTile();
            countScore();
        } else
            changeSelectedTile(tileId);
    }






}
























