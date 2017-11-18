package com.m224.infectious.task;

import android.os.AsyncTask;

import com.m224.infectious.activities.GameActivity;

/**
 * Created by 224 on 2017-11-18.
 */

public class AIComputeTask extends AsyncTask<Void, Void, Void> {
    private GameActivity gameActivity;

    public AIComputeTask(GameActivity gameActivity1) {
        this.gameActivity = gameActivity1;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //gameActivity.foo();
    }


    
}




























