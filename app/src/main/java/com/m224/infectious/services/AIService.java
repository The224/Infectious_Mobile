package com.m224.infectious.services;

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
 * Created by 224 on 2017-11-08.
 */

public class AIService extends GameService {
    private List<Movement> movements;
    private AIDifficulty difficulty;
    private Board originBoard;

    private class Movement {
        protected int origin;
        protected int destination;
        protected int points;

        public Movement(int origin, int destination, int points) {
            this.origin = origin;
            this.destination = destination;
            this.points = points;
        }
    }

    public AIService(AIDifficulty difficulty, Board board) {
        super(board);
        this.difficulty = difficulty;
        this.movements = new ArrayList<>();
    }

    public void template() {
        /*
        pour tout les case
            getAllmovementDispo
            pour allmovementDispo
                    get infect number
                    create movement obj

        sort list of movement

        mathrandom on array depend of difficulty*/
    }



    public void computeMove() {
        List<Movement> movements = new ArrayList<>();

        for (Tile tile: getBoard().getTiles()) {
            if (tile.getState() == State.PLAYER2) {
                int[] dispoMove = getAllDispoMove();

                for (int i : dispoMove) {
                    /// Infect around sur le meme board !!!!! reset board a chaque fois sinon !!!!
                    /// Need to simulate infect
                    int points = infectAround(i);

                    movements.add(new Movement(tile.getId(),i,points));
                }
            }
        }
    }

    // Besoin de recuperser les vrai movement disponible !
    public int[] getAllDispoMove() {
        int[] test = {2,3,5};
        return test;
    }

    /**
     * Faire une list des dix meilleurs coups
     * Math.random
     *      Facile    - 10-1
     *      Moyen     - 7-1
     *      Difficile - 4-1
     *      Extreme   - 1-1
     */

}

/*Implement MinMax one day !*/

















