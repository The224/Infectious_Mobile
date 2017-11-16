package com.m224.infectious.services;

import com.m224.infectious.domaine.Tile;
import com.m224.infectious.utils.AIDifficulty;
import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 224 on 2017-11-08.
 */

public class AIService  {
    //                tileId  points
    private List<Movement> movements;
    private AIDifficulty difficulty;

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

    public AIService(AIDifficulty difficulty) {
        this.difficulty = difficulty;
        this.movements = new ArrayList<>();
    }



    public int requestMovement(List<Tile> tiles) {



        fillMovements(tiles);
        sortMovements();
        return -1;
    }

    public void fillMovements(List<Tile> tiles) {

        List<Tile> copyTiles =  tiles;

        for (int i = 0; i < GridConfig.MAX_TILE; i++) {
            for (int j = 0; j < GridConfig.MAX_TILE; j++) {
                computeMove(copyTiles, i, j);
                copyTiles = tiles;
            }
        }
    }

    public void computeMove(List<Tile> tiles, int origin, int destination) {
        int points = 0;


        for (Tile tile : tiles) {
            if (tile.getState() == State.PLAYERAI)
                points++;
        }

    }








    public void sortMovements() {
        Collections.sort(movements, new Comparator<Movement>() {
            @Override
            public int compare(Movement m1, Movement m2) {
                if (m1.points > m2.points)
                    return -1;
                else if (m1.points < m2.points)
                    return 1;
                return 0;
            }
        });
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