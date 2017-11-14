package com.m224.infectious.domaine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.m224.infectious.R;
import com.m224.infectious.utils.GameType;

/**
 * Created by 224 on 2017-11-13.
 */

public class CardConfig {
    private String title;
    private GameType gameType;
    private int configId;

    public CardConfig(String title, GameType gameType, int configId) {
        this.title = title;
        this.gameType = gameType;
        this.configId = configId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }
}
