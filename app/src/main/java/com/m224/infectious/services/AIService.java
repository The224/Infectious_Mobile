package com.m224.infectious.services;

import android.support.v4.util.Pair;

import com.m224.infectious.utils.AIDifficulty;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by 224 on 2017-11-08.
 */

public class AIService {
    //                   tileId  points
    private List<Pair<Integer,Integer>> bestMove;
    public AIDifficulty difficulty;

    public AIService(AIDifficulty difficulty) {
        this.difficulty = difficulty;
        this.bestMove = new ArrayList<>();
    }

    public int requestMovement() {
        bestMove.add(Pair.create(12,0));
        bestMove.add(Pair.create(54,2));
        bestMove.add(Pair.create(23,3));
        bestMove.add(Pair.create(6,4));
        bestMove.add(Pair.create(72,8));
        bestMove.add(Pair.create(49,3));
        bestMove.add(Pair.create(28,7));
        bestMove.add(Pair.create(68,1));
        bestMove.add(Pair.create(65,0));
        bestMove.add(Pair.create(24,5));



        return bestMove.get(1).first;
    }

    public void sortBestMovement() {
        Collections.sort(bestMove, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                if (p1.second > p2.second)
                    return -1;
                else if (p1.second < p2.second)
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
