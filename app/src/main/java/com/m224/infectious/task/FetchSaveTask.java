package com.m224.infectious.task;

import android.os.AsyncTask;

import com.m224.infectious.activities.MainActivity;
import com.m224.infectious.domaine.Board;
import com.m224.infectious.domaine.Save;
import com.m224.infectious.sql.SaveTable;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 224 on 2017-11-14.
 */

public class FetchSaveTask extends AsyncTask<Void, Void, Void> {
    public MainActivity mainActivity;
    private List<Board> saveGames;

    public FetchSaveTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        saveGames = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SaveTable saveTable = new SaveTable(mainActivity);
        saveTable.open();
        List<Save> saves = saveTable.getAllSave();
        saveTable.close();

        for (Save save : saves) {
            Board board = Util.JSONToBoard(save.getJsonBoard());
            saveGames.add(board);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        mainActivity.updateSaveGames(saveGames);
    }
}