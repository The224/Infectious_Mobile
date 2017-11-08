package com.m224.infectious.domaine;

import com.m224.infectious.utils.ConfigVariable;
import com.m224.infectious.utils.State;

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

    private int configId;

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

    public void switchTurn() {
        turnPlayerOne = !turnPlayerOne;
        updateScore(); ////////////// Effet de board ???
    }
    /* *************** */

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

    /* *Board Configuration* */
    public void setConfigZero() {
        resetBoard();
        tiles.get(0).setState(State.PLAYER2);
        tiles.get(8).setState(State.PLAYER1);
        tiles.get(72).setState(State.PLAYER1);
        tiles.get(80).setState(State.PLAYER2);
        updateScore();
        configId = 0;
    }

    public void setConfigOne() {
        resetBoard();
        tiles.get(0).setState(State.PLAYER1);
        tiles.get(8).setState(State.PLAYER2);
        tiles.get(72).setState(State.PLAYER2);
        tiles.get(80).setState(State.PLAYER1);
        tiles.get(51).setState(State.BLOCK);tiles.get(60).setState(State.BLOCK);
        tiles.get(59).setState(State.BLOCK);tiles.get(57).setState(State.BLOCK);
        tiles.get(56).setState(State.BLOCK);tiles.get(47).setState(State.BLOCK);
        tiles.get(29).setState(State.BLOCK);tiles.get(20).setState(State.BLOCK);
        tiles.get(21).setState(State.BLOCK);tiles.get(23).setState(State.BLOCK);
        tiles.get(24).setState(State.BLOCK);tiles.get(33).setState(State.BLOCK);
        updateScore();
        configId = 1;
    }

    public void setConfigTwo() {
        resetBoard();
        tiles.get(0).setState(State.PLAYER1);
        tiles.get(8).setState(State.PLAYER2);
        tiles.get(72).setState(State.PLAYER2);
        tiles.get(80).setState(State.PLAYER1);
        tiles.get(40).setState(State.BLOCK);tiles.get(39).setState(State.BLOCK);
        tiles.get(41).setState(State.BLOCK);tiles.get(48).setState(State.BLOCK);
        tiles.get(49).setState(State.BLOCK);tiles.get(50).setState(State.BLOCK);
        tiles.get(30).setState(State.BLOCK);tiles.get(31).setState(State.BLOCK);
        tiles.get(32).setState(State.BLOCK);
        updateScore();
        configId = 2;
    }

    public void setConfigThree() {
        resetBoard();
        tiles.get(0).setState(State.PLAYER1);
        tiles.get(8).setState(State.PLAYER2);
        tiles.get(72).setState(State.PLAYER2);
        tiles.get(80).setState(State.PLAYER1);
        tiles.get(13).setState(State.BLOCK);tiles.get(22).setState(State.BLOCK);
        tiles.get(31).setState(State.BLOCK);tiles.get(40).setState(State.BLOCK);
        tiles.get(39).setState(State.BLOCK);tiles.get(38).setState(State.BLOCK);
        tiles.get(37).setState(State.BLOCK);tiles.get(41).setState(State.BLOCK);
        tiles.get(42).setState(State.BLOCK);tiles.get(43).setState(State.BLOCK);
        tiles.get(49).setState(State.BLOCK);tiles.get(58).setState(State.BLOCK);
        tiles.get(67).setState(State.BLOCK);
        updateScore();
        configId = 3;
    }
    /* ********************* */
}





















































