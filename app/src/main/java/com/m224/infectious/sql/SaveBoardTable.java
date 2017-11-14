package com.m224.infectious.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 224 on 2017-11-13.
 */

public class SaveBoardTable {

    private static final int VERSION_DB = 1;
    private static final String NAME_DB = "infectious.db";

    private static final String TABLE_SAVE = "SAVE";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_JSONBOARD = "JSONBOARD";

    private SQLiteDatabase db;
    private OpenHelper sqlDataBase;

    public SaveBoardTable(Context context){
        sqlDataBase = new OpenHelper(context, NAME_DB, null, VERSION_DB);
    }

    public void open(){
        db = sqlDataBase.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDB(){
        return db;
    }

    public long insertSave(String name, String jsonBoard){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_JSONBOARD, jsonBoard);
        return db.insert(TABLE_SAVE, null, values);
    }

    public int updateContact(int id, String name, String jsonBoard){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_JSONBOARD, jsonBoard);
        return db.update(TABLE_SAVE, values, COL_ID + " = " +id, null);
    }

    public int removeContactWithID(int id){
        return db.delete(TABLE_SAVE, COL_ID + " = " +id, null);
    }





}
