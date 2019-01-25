package com.example.comp.imagelist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myDB";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_NAME + " ("
                + "url text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getCursorOnUrl(String url) {
        SQLiteDatabase db = getReadableDatabase();
        String request = "SELECT * FROM " + DB_NAME + " WHERE url = '" + url + "'";
        return db.rawQuery(request, null);
    }

    public Cursor getCursor() {
        SQLiteDatabase database = getReadableDatabase();
        return database.query(DB_NAME, null, null, null, null, null, null);
    }
}
