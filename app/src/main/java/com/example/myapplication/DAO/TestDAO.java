package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DTO.TestDTO;
import com.example.myapplication.DataBaseHelper.ListTestDataBase;

import java.util.ArrayList;

public class TestDAO {
    ListTestDataBase listTestDataBase;
    SQLiteDatabase sqLiteDatabase;

    public TestDAO(Context context) {
        listTestDataBase = new ListTestDataBase(context);
        sqLiteDatabase = listTestDataBase.getWritableDatabase();
    }

    public long insertTest(TestDTO testDTO) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_test", testDTO.getDate_test());
        contentValues.put("test_shift", testDTO.getTest_shift());
        contentValues.put("test_room", testDTO.getTest_room());
        contentValues.put("test_subject", testDTO.getTest_subject());
        try {
            result = sqLiteDatabase.insert("list_test", null, contentValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //update
    public int updateTest(TestDTO testDTO) {
        int result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_test", testDTO.getDate_test());
        contentValues.put("test_shift", testDTO.getTest_shift());
        contentValues.put("test_room", testDTO.getTest_room());
        contentValues.put("test_subject", testDTO.getTest_subject());

        String[] condition = {String.valueOf(testDTO.getId())};
        try {
            result = sqLiteDatabase.update("list_test", contentValues, "id=?", condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteTest(TestDTO testDTO) {
        int result = -1;
        String[] condition = {String.valueOf(testDTO.getId())};
        try {
            result = sqLiteDatabase.delete("list_test", "id=?", condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<TestDTO> getAllTest() {
        ArrayList<TestDTO> listTest = new ArrayList<>();
        try {
            String sql = "SELECT * FROM list_test";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String date_test = cursor.getString(1);
                    String test_shift = cursor.getString(2);
                    String test_room = cursor.getString(3);
                    String test_subject = cursor.getString(4);
                    listTest.add(new TestDTO(id, date_test, test_shift, test_room, test_subject));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTest;
    }
}
