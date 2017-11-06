package com.m224.ataxx.domaine;

import com.m224.ataxx.utils.ConfigVariable;
import com.m224.ataxx.utils.State;

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
        for (int i = 0; i < ConfigVariable.MAX_TILE; i++)
            tiles.add(new Tile(State.EMPTY,i));
    }

    /* *Public Method* */
    public Tile getTileAt(int i) {
        return tiles.get(i);
    }

    public State getStateAt(int i) {
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
        tiles.get(0).setState(State.PLAYER2);
        tiles.get(8).setState(State.PLAYER1);
        tiles.get(72).setState(State.PLAYER1);
        tiles.get(80).setState(State.PLAYER2);
        updateScore();
    }

    public void setConfigTwo() {
        resetBoard();
        tiles.get(0).setState(State.PLAYER1);
        tiles.get(8).setState(State.PLAYER2);
        tiles.get(72).setState(State.PLAYER2);
        tiles.get(80).setState(State.PLAYER1);
        updateScore();
    }
    /* ********************* */

    /* *Private Method* */
    private void updateScore() {
        scorePlayer1=0; scorePlayer2=0;
        for (Tile tile : tiles) {
            if (tile.getState() == State.PLAYER1)
                scorePlayer1++;
            if (tile.getState() == State.PLAYER2)
                scorePlayer2++;
        }
    }

    private void resetBoard() {
        for (Tile tile : tiles)
            tile.setState(State.EMPTY);
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






















































