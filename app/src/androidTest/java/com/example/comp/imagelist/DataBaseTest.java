package com.example.comp.imagelist;

import android.support.test.runner.AndroidJUnit4;

import com.example.comp.imagelist.adapter.Photo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class DataBaseTest {

    private static final String ID = String.valueOf(System.currentTimeMillis());
    private static final String SMALL_URL = "smallurl";
    private static final String FULL_URL = "fullurl";
    private static final String DESCRIPTION = "description";

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Test
    public void checkDataBaseInterface() {

        final Photo photo = new Photo(ID, SMALL_URL, FULL_URL, DESCRIPTION);
        MyApplication.dataBaseHelper.insertPhoto(photo);
        Assert.assertTrue(MyApplication.dataBaseHelper.containsPhoto(photo.getId()));

        MyApplication.dataBaseHelper.deletePhoto(photo.getId());
        Assert.assertTrue(!MyApplication.dataBaseHelper.containsPhoto(photo.getId()));

        MyApplication.dataBaseHelper.insertPhoto(photo);
        compositeDisposable.add(MyApplication.dataBaseHelper.getPhoto(photo.getId()).subscribe(new Consumer<Photo>() {
            @Override
            public void accept(Photo gPhoto) {
                Assert.assertEquals(photo.getId(), gPhoto.getId());
                Assert.assertEquals(photo.getSmallUrl(), gPhoto.getSmallUrl());
                Assert.assertEquals(photo.getFullUrl(), gPhoto.getFullUrl());
                Assert.assertEquals(photo.getDescription(), gPhoto.getDescription());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {

            }
        }, new Action() {
            @Override
            public void run() {
                MyApplication.dataBaseHelper.deletePhoto(photo.getId());
            }
        }));
    }
}
