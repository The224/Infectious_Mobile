package com.m224.infectious.services;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.m224.infectious.domaine.Board;
import com.m224.infectious.domaine.Tile;
import com.m224.infectious.utils.AIDifficulty;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.State;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Semili Singleton
 */

public class AIService {
    private List<Movement> movements;

    private class Movement implements Comparable<Movement> {
        private int origin;
        private int destination;
        private int score;

        private Movement(int origin, int destination, int score) {
            this.origin = origin;
            this.destination = destination;
            this.score = score;
        }

        @Override
        public int compareTo(@NonNull Movement movement) {
            return 0;
        }
    }

    public Pair<Integer,Integer> compute(AIDifficulty difficulty, Board board) {
        this.movements = new ArrayList<>();

        fillMovement(board);
        sortResult();
        Movement movement = getRandomMove(difficulty);
        movements.clear();
        return new Pair<>(movement.origin,movement.destination);
    }

    private void fillMovement(Board board) {
        // MUCH WOW !
        for (int i = 0; i < GridConfig.MAX_TILE; i++) { // For
            if (board.getStateAt(i) == State.PLAYER2) { // only the tile of the computer
                List<Integer> validMove = Util.getValidMove(i);
                for (int j : validMove) {
                    if (board.getStateAt(j) == State.EMPTY && i != j) {
                        int k = getTileScore(board, j);
                        movements.add(new Movement(i,j,k));
                    }
                }
            }
        }
    }

    private void sortResult() {
        movements.sort(new Comparator<Movement>() {
            @Override
            public int compare(Movement m1, Movement m2) {
                if (m1.score < m2.score)
                    return 1;
                if (m1.score > m2.score)
                    return -1;
                return 0;
            }
        });
    }

    private Movement getRandomMove(AIDifficulty difficulty) { // TODO : add magic number to GridConfig ???
        int max;
        if (difficulty == AIDifficulty.EASY)
            max = 50;
        else if (difficulty == AIDifficulty.MEDIUM)
            max = 20;
        else if (difficulty == AIDifficulty.HARD)
            max = 5;
        else
            max = 1;

        int aiChoice = (int)(Math.random()* max);

        return movements.get(aiChoice);
    }


    private int getTileScore(Board board, int tileId) {
        int nbInfect = 0;
        List<Integer> integerList = Util.getTileAround(tileId);
        for (int id : integerList ) {
            if (board.getTileAt(id).getState() == State.PLAYER1) {
                nbInfect++;
            }
        }
        return nbInfect;
    }
}

















