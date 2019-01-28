package com.example.comp.imagelist;

import com.example.comp.imagelist.adapter.Photo;

import java.util.List;

import io.reactivex.Observable;

interface DataBaseHelperInterface {
    void insertPhoto(Photo photo);

    Observable<Photo> getPhoto(String id);

    void deletePhoto(String id);

    boolean containsPhoto(String id);

    Observable<List<Photo>> getAllPhotos();
}
