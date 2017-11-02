package com.m224.ataxx.services;

import com.m224.ataxx.domaine.Tile;
import com.m224.ataxx.utils.IGlobalVariable;
import com.m224.ataxx.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 224 on 2017-10-23.
 */

public class GameService {

    private List<Tile> tiles;
    private Tile selectTile = null;

    private boolean turnPlayerOne;

    private int scorePlayer1;
    private int scorePlayer2;

    public GameService() {
        this.tiles = new ArrayList<>();

        for (int i = 0; i < IGlobalVariable.MAX_TILE; i++)
            tiles.add(new Tile(IGlobalVariable.STATE.EMPTY,i));

        turnPlayerOne = true;
    }

    public boolean isTurnPlayerOne() {
        return turnPlayerOne;
    }

    public Tile getTileAt(int id) {
        return tiles.get(id);
    }
    public int getScorePlayer1() {
        return scorePlayer1;
    }
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void move(int toId){
        if (selectTile == null) {  // Cree la selection

            if (tiles.get(toId).getState() == IGlobalVariable.STATE.PLAYER1 && turnPlayerOne ||
                    tiles.get(toId).getState() == IGlobalVariable.STATE.PLAYER2 && !turnPlayerOne) {
                selectTile = tiles.get(toId);
                selectTile.setSelected(true);
            }
        } else if (tiles.get(toId).isStatePlayer()) { // Change la selection
            selectTile.setSelected(false);
            selectTile = tiles.get(toId);
            selectTile.setSelected(true);
        } else if (tiles.get(toId).getState() == IGlobalVariable.STATE.EMPTY) {

            if (turnPlayerOne && selectTile.getState() == IGlobalVariable.STATE.PLAYER1 ||
                    !turnPlayerOne && selectTile.getState() == IGlobalVariable.STATE.PLAYER2) {
                makeMovement(toId);
            }
        }
    }

    public void setConfigOne() {
        resetGrid();
        tiles.get(0).setState(IGlobalVariable.STATE.PLAYER2);
        tiles.get(8).setState(IGlobalVariable.STATE.PLAYER1);
        tiles.get(72).setState(IGlobalVariable.STATE.PLAYER1);
        tiles.get(80).setState(IGlobalVariable.STATE.PLAYER2);
        countScore();
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

    /**
     * Infect the 8 tiles around param int*/
    private void infectAround(int aroundId) {
        List<Integer> integerList = Util.getTileAround(aroundId);
        for (int id : integerList ) {
            if (tiles.get(id).isStatePlayer())
                tiles.get(id).setState(tiles.get(aroundId).getState());
        }
    }

    private void makeMovement(int toId) {
        int moveType = Util.confirmValidMove(selectTile.getId(),toId);

        if (moveType > 0) {
            selectTile.setSelected(false);
            tiles.get(toId).setState(selectTile.getState());
            infectAround(toId);

            if (moveType == 2) {
                selectTile.setState(IGlobalVariable.STATE.EMPTY);
            }
            selectTile = null;
            countScore();
            turnPlayerOne = !turnPlayerOne;
        }
    }


}
























