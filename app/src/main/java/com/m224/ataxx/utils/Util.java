package com.m224.ataxx.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 224 on 2017-10-23.
 */

public class Util {


    public static List<Integer> getTileAround(int tileId) {
        int rowLength = (int)Math.sqrt(IGlobalVariable.MAX_TILE);
        List<Integer> around = new ArrayList<>();
        boolean upLeft = true, upRight = true, downLeft = true, downRight = true;

        if (tileId > rowLength) { // Ne se situe pas au sommet
            around.add(tileId-rowLength);
        } else {
            upLeft = false;
            upRight = false;
        }

        if (tileId < (IGlobalVariable.MAX_TILE - rowLength) ) { // Ne se situe pas au sol
            around.add(tileId+rowLength);
        } else {
            downLeft = false;
            downRight = false;
        }

        if ((tileId%rowLength) > 0 ) { // Ne se situe pas a gauche
            around.add(tileId-1);
        } else {
            downLeft = false;
            upLeft = false;
        }

        if ((tileId%rowLength) < 8 ) { // Ne se situe pas a droite
            around.add(tileId+1);
        } else {
            upRight = false;
            downRight = false;
        }

        if (upLeft)
            around.add( tileId-(rowLength+1) );
        if (upRight)
            around.add( tileId-(rowLength-1) );
        if (downLeft)
            around.add( tileId+(rowLength-1) );
        if (downRight)
            around.add( tileId+(rowLength+1) );

        return around;
    }

    /**
     * lazyness ?
     * @param start tile id of the origin point
     * @param end tile id of the end move
     * @return 0 if move is not valid
     *         1 if duplicated
     *         2 if jump
     */
    public static int confirmValidMove(int start, int end) {
        List<Integer> duplicates = getTileAround(start);

        for (int duplicate : duplicates) {
            if (duplicate == end) {
                return 1;
            }
        }

        for (int duplicate : duplicates) {
            List<Integer> jumps = getTileAround(duplicate);
            for (int jump : jumps) {
                if (jump == end) {
                    return 2;
                }
            }
        }

        return 0;
    }


}
