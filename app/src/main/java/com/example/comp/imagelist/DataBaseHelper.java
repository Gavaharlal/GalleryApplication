package com.example.comp.imagelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.comp.imagelist.adapter.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseHelper extends SQLiteOpenHelper implements DataBaseHelperInterface {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SMALL_URL = "smallUrl";
    private static final String COLUMN_FULL_URL = "fullUrl";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String TABLE_NAME = "myDataBaseTable";

    DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " TEXT" + ", "
                + COLUMN_SMALL_URL + " TEXT" + ", "
                + COLUMN_FULL_URL + " TEXT" + ", "
                + COLUMN_DESCRIPTION + " TEXT"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void insertPhoto(final Photo photo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, photo.getId());
        values.put(COLUMN_SMALL_URL, photo.getSmallUrl());
        values.put(COLUMN_FULL_URL, photo.getFullUrl());
        values.put(COLUMN_DESCRIPTION, photo.getDescription());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    @Override
    public Observable<Photo> getPhoto(final String id) {

        final Callable<Photo> photoCallable = new Callable<Photo>() {
            @Override
            public Photo call() {
                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?",
                        new String[]{id}, null, null, null);

                Photo photo;

                if (cursor.moveToFirst()) {
                    photo = new Photo(
                            id,
                            cursor.getString(cursor.getColumnIndex(COLUMN_SMALL_URL)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_FULL_URL)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    );
                    cursor.close();
                } else {
                    photo = null;
                }

                return photo;
            }
        };
        return Observable.fromCallable(photoCallable).subscribeOn(Schedulers.io());
    }

    @Override
    public void deletePhoto(final String id) {
        new Callable<Void>() {
            @Override
            public Void call() {
                SQLiteDatabase db = getWritableDatabase();
                db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                        new String[]{String.valueOf(id)});
                db.close();
                return null;
            }
        }.call();
    }

    @Override
    public boolean containsPhoto(final String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?",
                new String[]{id}, null, null, null);
        boolean ans = cursor.moveToFirst();
        cursor.close();
        return ans;
    }

    @Override
    public Observable<List<Photo>> getAllPhotos() {

        final Callable<List<Photo>> listCallable = new Callable<List<Photo>>() {
            @Override
            public List<Photo> call() {
                List<Photo> photos = new ArrayList<>();

                String selectQuery = "SELECT * FROM " + TABLE_NAME;

                SQLiteDatabase db = getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {
                        Photo photo = new Photo(
                                cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_SMALL_URL)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_FULL_URL)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                        );
                        photos.add(photo);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();

                return photos;
            }
        };
        return Observable.fromCallable(listCallable);
    }

}
