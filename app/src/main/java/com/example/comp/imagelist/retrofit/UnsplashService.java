package com.example.comp.imagelist.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UnsplashService {

    @GET
    Observable<List<ModelPhoto>> getModelPhotos(@Url String request);

}
