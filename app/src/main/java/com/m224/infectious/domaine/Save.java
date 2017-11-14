package com.m224.infectious.domaine;

/**
 * Created by 224 on 2017-11-14.
 */

public class Save {

    private int id;
    private String name;
    private String jsonBoard;

    public Save(int id, String name, String jsonBoard) {
        this.id = id;
        this.name = name;
        this.jsonBoard = jsonBoard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonBoard() {
        return jsonBoard;
    }

    public void setJsonBoard(String jsonBoard) {
        this.jsonBoard = jsonBoard;
    }
}
