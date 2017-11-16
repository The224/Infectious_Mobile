package com.m224.infectious;

import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.Util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 224 on 2017-10-26.
 */

public class UtilTest {
    @Test
    public void tileAround10Test() throws Exception {
        assertEquals(81, GridConfig.MAX_TILE);

        List<Integer> returnList = Util.getTileAround(10);

        assertEquals(8, returnList.size());
        assertTrue(returnList.contains(0));
        assertTrue(returnList.contains(20));
    }

    @Test
    public void tileAround80Test() throws Exception {
        assertEquals(81, GridConfig.MAX_TILE);

        List<Integer> returnList = Util.getTileAround(80);

        assertEquals(3, returnList.size());
        assertFalse(returnList.contains(81));
        assertFalse(returnList.contains(89));
        assertTrue(returnList.contains(79));
    }

    @Test
    public void tileAround72Test() throws Exception {
        assertEquals(81, GridConfig.MAX_TILE);

        List<Integer> returnList = Util.getTileAround(72);

        assertEquals(3, returnList.size());
        assertFalse(returnList.contains(71));
        assertTrue(returnList.contains(63));
    }

}
