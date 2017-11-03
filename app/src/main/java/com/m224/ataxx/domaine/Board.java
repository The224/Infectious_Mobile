package com.m224.ataxx.domaine;

import com.m224.ataxx.utils.IGlobalVariable;
import com.m224.ataxx.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 224 on 2017-11-03.
 */

public class Board {

    private List<Tile> tiles;
    private Tile selectTile;

    private boolean turnPlayerOne;

    private int scorePlayer1;
    private int scorePlayer2;

    public Board() {
        this.tiles = new ArrayList<>();
        selectTile = null;
        turnPlayerOne = true;
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        for (int i = 0; i < IGlobalVariable.MAX_TILE; i++)
            tiles.add(new Tile(IGlobalVariable.STATE.EMPTY,i));
    }

    /* *Public Method* */
    public Tile getTileAt(int i) {
        return tiles.get(i);
    }

    public IGlobalVariable.STATE getStateAt(int i) {
        return tiles.get(i).getState();
    }

    public void swithTurn() {
        turnPlayerOne = !turnPlayerOne;
        updateScore(); ////////////// Effet de board ???
    }
    /* *************** */

    /* *Board Configuration* */
    public void setConfigOne() {
        resetBoard();
        tiles.get(0).setState(IGlobalVariable.STATE.PLAYER2);
        tiles.get(8).setState(IGlobalVariable.STATE.PLAYER1);
        tiles.get(72).setState(IGlobalVariable.STATE.PLAYER1);
        tiles.get(80).setState(IGlobalVariable.STATE.PLAYER2);
        updateScore();
    }

    public void setConfigTwo() {
        resetBoard();
        tiles.get(0).setState(IGlobalVariable.STATE.PLAYER1);
        tiles.get(8).setState(IGlobalVariable.STATE.PLAYER2);
        tiles.get(72).setState(IGlobalVariable.STATE.PLAYER2);
        tiles.get(80).setState(IGlobalVariable.STATE.PLAYER1);
        updateScore();
    }
    /* ********************* */

    /* *Private Method* */
    private void updateScore() {
        scorePlayer1=0; scorePlayer2=0;
        for (Tile tile : tiles) {
            if (tile.getState() == IGlobalVariable.STATE.PLAYER1)
                scorePlayer1++;
            if (tile.getState() == IGlobalVariable.STATE.PLAYER2)
                scorePlayer2++;
        }
    }

    private void resetBoard() {
        for (Tile tile : tiles)
            tile.setState(IGlobalVariable.STATE.EMPTY);
    }
    /* **************** */

    /* *Getter & Setter* */
    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getSelectTile() {
        return selectTile;
    }

    public void setSelectTile(int id) {
        if(id<0)
            this.selectTile = null;
        else
            this.selectTile = tiles.get(id);
    }

    public boolean isTurnPlayerOne() {
        return turnPlayerOne;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }
    /* ***************** */
}






















































