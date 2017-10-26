package com.m224.ataxx;

import com.m224.ataxx.utils.IGlobalVariable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by 224 on 2017-10-26.
 */

public class GameServiceTest {
    @Test
    public void test() throws Exception {
        assertEquals(81, IGlobalVariable.MAX_TILE);

    }
}
