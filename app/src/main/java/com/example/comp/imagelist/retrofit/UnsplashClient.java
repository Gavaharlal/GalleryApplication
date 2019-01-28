package com.example.comp.imagelist.retrofit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class UnsplashClient {

    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com/";

    public UnsplashService createUnsplashService() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(UNSPLASH_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(UnsplashService.class);
    }
}
