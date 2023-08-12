package com.example.myapplication.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListTestDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ListTest.db";
    private static final int DATABASE_VERSION = 1;

    public ListTestDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableListTest = "CREATE TABLE list_test(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date_test TEXT NOT NULL," +
                "test_shift TEXT NOT NULL," +
                "test_room TEXT NOT NULL," +
                "test_subject TEXT NOT NULL)";

        db.execSQL(createTableListTest);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
