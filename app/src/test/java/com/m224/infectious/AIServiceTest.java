package com.m224.infectious;

import com.m224.infectious.services.AIService;
import com.m224.infectious.utils.AIDifficulty;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by 224 on 2017-11-08.
 */

public class AIServiceTest {
    @Test
    public void requestMovementTest() {
        AIService aiServiceTest = new AIService(AIDifficulty.EASY);


        assertEquals(72, aiServiceTest.requestMovement());



    }
}
