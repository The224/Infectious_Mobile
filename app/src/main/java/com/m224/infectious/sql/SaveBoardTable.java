package com.m224.infectious.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.m224.infectious.R;
import com.m224.infectious.domaine.Save;

import java.util.ArrayList;
import java.util.List;

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
    private Context context;

    public SaveBoardTable(Context context){
        sqlDataBase = new OpenHelper(context, NAME_DB, null, VERSION_DB);
        this.context = context;
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

    public int updateSave(int id, String name, String jsonBoard){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_JSONBOARD, jsonBoard);
        return db.update(TABLE_SAVE, values, COL_ID + " = " +id, null);
    }

    public int removeSaveWithID(int id){
        return db.delete(TABLE_SAVE, COL_ID + " = " +id, null);
    }

    public long insertQuickSave(String jsonBoard){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, context.getResources().getString(R.string.quick_save));
        values.put(COL_JSONBOARD, jsonBoard);
        values.put(COL_ID, -1);

        removeSaveWithID(-1);

        return db.insert(TABLE_SAVE, null, values);
    }

    public List<Save> getAllSave(){
        List<Save> saves = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_SAVE,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String jsonBoard = cursor.getString(cursor.getColumnIndex(COL_JSONBOARD));

                saves.add(new Save(id,name,jsonBoard));

                cursor.moveToNext();
            }
        }
        return saves;
    }

}
