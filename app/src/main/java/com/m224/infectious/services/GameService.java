package com.m224.infectious.services;

import com.m224.infectious.domaine.Board;
import com.m224.infectious.domaine.Config;
import com.m224.infectious.utils.State;
import com.m224.infectious.utils.Util;

import java.util.List;

/**
 * Created by 224 on 2017-10-23.
 */

public class GameService {

    /**
     * Donc, je suis obliger d'avoir board pour sauver les donnees en SQL,
     * Justement, y reste le sql a implementer
     * Et computer a gerer
     */















    private Board board;

    public GameService(Config config, Board board) {
        if (board != null) {
            this.board = board;
        }
        else {
            this.board = new Board(config);
        }
    }

    public void restart() {
        board.restart();
    }

    public int getScorePlayer1() {
        return board.getScorePlayer1();
    }

    public int getScorePlayer2() {
        return board.getScorePlayer2();
    }

    public State getStateAt(int id) {
        return board.getStateAt(id);
    }

    public boolean isSelectAt(int id) {
        return board.getTileAt(id).isSelected();
    }

    public boolean isTurnPlayerOne() {
        return board.isTurnPlayerOne();
    }

    public void move(int toId){
        if (board.getSelectTile() == null) {  // Cree la selection
            if (board.getStateAt(toId) == State.PLAYER1 && board.isTurnPlayerOne() ||
                    board.getStateAt(toId) == State.PLAYER2 && !board.isTurnPlayerOne()) {
                board.setSelectTile(toId);
                board.getSelectTile().setSelected(true);
            }
        } else if (board.getTileAt(toId).isStatePlayer()) { // Change la selection
            board.getSelectTile().setSelected(false);
            board.setSelectTile(toId);
            board.getSelectTile().setSelected(true);

        } else if (board.getStateAt(toId) == State.EMPTY) {

            if (board.isTurnPlayerOne() && board.getSelectTile().getState() == State.PLAYER1 ||
                    !board.isTurnPlayerOne() && board.getSelectTile().getState() == State.PLAYER2) {
                makeMovement(toId);
            }
        }
    }

    /**
     * Infect the 8 tiles around param int*/
    private void infectAround(int aroundId) {
        List<Integer> integerList = Util.getTileAround(aroundId);
        for (int id : integerList ) {
            if (board.getTileAt(id).isStatePlayer())
                board.getTileAt(id).setState(board.getStateAt(aroundId));
        }
    }

    private void makeMovement(int toId) {
        int moveType = Util.confirmValidMove(board.getSelectTile().getId(),toId);

        if (moveType > 0) {
            board.getSelectTile().setSelected(false);
            board.getTileAt(toId).setState(board.getSelectTile().getState());
            infectAround(toId);

            if (moveType == 2) {
                board.getSelectTile().setState(State.EMPTY);
            }
            board.setSelectTile(-1);
            board.switchTurn();
        }
    }

    public Board getBoard() {
        return board;
    }
}
























