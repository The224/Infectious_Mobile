package com.m224.infectious.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.m224.infectious.activities.GameActivity;
import com.m224.infectious.domaine.Board;
import com.m224.infectious.services.AIService;
import com.m224.infectious.services.GameService;
import com.m224.infectious.utils.AIDifficulty;
import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.State;
import com.m224.infectious.utils.Util;

import java.util.List;

/**
 * Created by 224 on 2017-11-18.
 */

public class AIComputeTask extends AsyncTask<Void, Void, Void> {
    private Board board;
    private AIDifficulty aiDifficulty;
    private AIService aiService;
    private GameActivity gameActivity;

    public AIComputeTask(Context context, AIDifficulty aiDifficulty) {
        GameActivity gameActivity = (GameActivity) context;
        this.board = gameActivity.getGameService().getBoard();
        this.gameActivity = gameActivity;
        this.aiDifficulty = aiDifficulty;
        this.aiService = new AIService();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        boolean stillAlive = true;
        Pair<Integer, Integer> result = null;

        while(stillAlive) {
            if (gameActivity == null)
                stillAlive = false;

            if (gameActivity != null && !gameActivity.getGameService().isTurnPlayerOne()) {
                result = aiService.compute(aiDifficulty,board);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameActivity.aiMove(result);
            }
        }
        return null;
    }
}




























