package com.m224.ataxx.services;

import android.content.Context;

import com.m224.ataxx.domaine.Tile;
import com.m224.ataxx.utils.IGlobalVariable;
import com.m224.ataxx.utils.Util;

import java.util.List;

/**
 * Created by 224 on 2017-10-23.
 */

public class GameService {

    private Tile[] tiles;
    private int selectTile = IGlobalVariable.DEFAULT_SELECTED_TILE;

    private int scorePlayer1;
    private int scorePlayer2;

    public GameService(Context context) {
        this.tiles = new Tile[IGlobalVariable.MAX_TILE];

        for (int i = 0; i < tiles.length; i++)
            tiles[i] = new Tile(context, IGlobalVariable.STATE.EMPTY);

        tiles[selectTile].setSelected(true);

    }

    public Tile getTileAt(int i) {
        return tiles[i];
    }
    public int getScorePlayer1() {
        return scorePlayer1;
    }
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    private void resetGrid() {
        for (Tile tile : tiles)
            tile.setState(IGlobalVariable.STATE.EMPTY);
    }

    private void countScore() {
        scorePlayer1=0; scorePlayer2=0;
        for (Tile tile : tiles) {
            if (tile.getState() == IGlobalVariable.STATE.PLAYER1)
                scorePlayer1++;
            if (tile.getState() == IGlobalVariable.STATE.PLAYER2)
                scorePlayer2++;
        }
    }

    public void setConfigOne() {
        resetGrid();
        tiles[0].setState(IGlobalVariable.STATE.PLAYER2);
        tiles[8].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[72].setState(IGlobalVariable.STATE.PLAYER1);
        tiles[80].setState(IGlobalVariable.STATE.PLAYER2);
        countScore();
    }

    private boolean isStatePlayer(int id) {
        if (tiles[id].getState() == IGlobalVariable.STATE.PLAYER1 ||
                tiles[id].getState() == IGlobalVariable.STATE.PLAYER2) {
            return true;
        }
        return false;
    }

    private void changeSelectedTile(int newSelected) {
        if (isStatePlayer(newSelected)) {
            removeSelection();
            tiles[newSelected].setSelected(true);
            selectTile = newSelected;
        }
    }

    public void removeSelection(){
        if (selectTile >= 0) {
            tiles[selectTile].setSelected(false);
        }
    }

    public void makeMoveOld(int tileId) {
        Tile moveTile = tiles[tileId];
        // Prevent selectTile to be null
        if (selectTile < 0) {
            changeSelectedTile(tileId);
            return;
        }

        if (moveTile.getState() == IGlobalVariable.STATE.EMPTY) {
            moveTile.setState(tiles[selectTile].getState());
            //tiles[selectTile].setState(IGlobalVariable.STATE.EMPTY);
            removeSelection();
            infectAround(tileId);
            countScore();
        } else
            changeSelectedTile(tileId);

        selectTile = -1;

    }

    private void infectAround(int tileId) {
        List<Integer> aroundId = Util.getTileAround(tileId);
        for (int id : aroundId) {
            Tile tile = tiles[id];
            if ( isStatePlayer(tileId) ) {
                tile.setState(tiles[selectTile].getState());
            }
        }
    }

    /*********************************/

    public boolean isFutureMoveValid(int id) {
        // Confirme que le mouvement se fais dans les deux premiere case
        return false;
    }

    public boolean isSelectionOk(int id) {
        // Doit confirmer que le choix est bon quand je click sur n'importe quel case
        return false;
    }

    public void makeMove(int id) {
        // fait le mouvement vers l'autre case
    }

    public void move(int toId){

        isFutureMoveValid(toId);

        isSelectionOk(toId);

        makeMove(toId);

        infectAround(toId);

        removeSelection();

    }














}
























