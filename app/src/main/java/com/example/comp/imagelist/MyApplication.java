package com.example.comp.imagelist;

import android.app.Application;

public class MyApplication extends Application {

    public static DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate() {
        dataBaseHelper = new DataBaseHelper(this);
        super.onCreate();
    }
}
