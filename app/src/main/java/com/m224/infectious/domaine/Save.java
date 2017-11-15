package com.m224.infectious.domaine;

/**
 * Created by 224 on 2017-11-14.
 */

public class Save {

    private int id;
    private String jsonBoard;

    public Save(int id, String jsonBoard) {
        this.id = id;
        this.jsonBoard = jsonBoard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonBoard() {
        return jsonBoard;
    }

    public void setJsonBoard(String jsonBoard) {
        this.jsonBoard = jsonBoard;
    }
}
