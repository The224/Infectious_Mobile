package com.m224.infectious.domaine;

import com.m224.infectious.utils.ConfigVariable;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 224 on 2017-11-03.
 */

public class Board {

    private int id;
    private String title;
    private GameType gameType;
    private int[] blockTile;

    private List<Tile> tiles;
    private Tile selectTile;

    private boolean turnPlayerOne;
    private int scorePlayer1;
    private int scorePlayer2;

    public Board(int[] blockTile) {
        this.tiles = new ArrayList<>();
        this.blockTile = blockTile;
        selectTile = null;
        turnPlayerOne = true;
        scorePlayer1 = 0;
        scorePlayer2 = 0;

        for (int i = 0; i < ConfigVariable.MAX_TILE; i++)
            tiles.add(new Tile(State.EMPTY,i));


        reset();
    }

    public void switchTurn() {
        turnPlayerOne = !turnPlayerOne;
        countScore();
    }

    private void reset() {
        for (Tile tile : tiles)
            tile.setState(State.EMPTY);

        for (int i : blockTile)
            tiles.get(i).setState(State.BLOCK);

        tiles.get(0).setState(State.PLAYER1);
        tiles.get(8).setState(State.PLAYER2);
        tiles.get(72).setState(State.PLAYER2);
        tiles.get(80).setState(State.PLAYER1);

        countScore();
    }

    private void countScore() {
        scorePlayer1=0; scorePlayer2=0;
        for (Tile tile : tiles) {
            if (tile.getState() == State.PLAYER1)
                scorePlayer1++;
            if (tile.getState() == State.PLAYER2)
                scorePlayer2++;
        }
    }

    /* *Getter & Setter* */
    public Tile getTileAt(int i) {
        return tiles.get(i);
    }

    public State getStateAt(int i) {
        return tiles.get(i).getState();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int[] getBlockTile() {
        return blockTile;
    }

    public void setBlockTile(int[] blockTile) {
        this.blockTile = blockTile;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile getSelectTile() {
        return selectTile;
    }

    public void setSelectTile(Tile selectTile) {
        this.selectTile = selectTile;
    }

    public boolean isTurnPlayerOne() {
        return turnPlayerOne;
    }

    public void setTurnPlayerOne(boolean turnPlayerOne) {
        this.turnPlayerOne = turnPlayerOne;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

/* ***************** */
}






















































