package com.m224.infectious;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by 224 on 2017-10-26.
 */

@RunWith(AndroidJUnit4.class)
public class GameActivityInstrumentedTest {
    @Test
    public void confirmWorkingGame() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.m224.ataxx", appContext.getPackageName());
    }



}
