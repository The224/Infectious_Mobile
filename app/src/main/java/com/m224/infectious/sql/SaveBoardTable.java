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


    public long insertSave(String jsonBoard){
        ContentValues values = new ContentValues();
        values.put(COL_JSONBOARD, jsonBoard);
        return db.insert(TABLE_SAVE, null, values);
    }

    public long insertQuickSave(String jsonBoard){
        removeSaveWithID(-1);

        ContentValues values = new ContentValues();
        values.put(COL_ID, -1);
        values.put(COL_JSONBOARD, jsonBoard);
        return db.insert(TABLE_SAVE, null, values);
    }

    public int removeSaveWithID(int id){
        return db.delete(TABLE_SAVE, COL_ID + " = " +id, null);
    }

    public List<Save> getAllSave(){
        List<Save> saves = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_SAVE,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String jsonBoard = cursor.getString(cursor.getColumnIndex(COL_JSONBOARD));

                saves.add(new Save(id,jsonBoard));

                cursor.moveToNext();
            }
        }
        return saves;
    }

    /**
     * DEBUG
     */
    public void drop() {
        db.execSQL("DROP TABLE " + TABLE_SAVE + ";");
    }

}
