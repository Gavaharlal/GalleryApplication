package com.example.comp.imagelist.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonParsable {
    @GET("photos?per_page=30&client_id=588504af4732dedfff1f7b64f0849b7bacb3d7ebf20e351f8bea66d084ef977b")
    Call<List<Photo>> parse();
}
