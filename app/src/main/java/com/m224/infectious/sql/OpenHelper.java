package com.m224.infectious.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 224 on 2017-11-13.
 */

public class OpenHelper extends SQLiteOpenHelper {

    private static final String TABLE_BOARD = "SAVE";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_JSONBOARD = "JSONBOARD";

    private static final String CREATE_DB = "CREATE TABLE " + TABLE_BOARD + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, "
            + COL_JSONBOARD + " TEXT NOT NULL);";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_BOARD + ";");
        onCreate(db);
    }

}
