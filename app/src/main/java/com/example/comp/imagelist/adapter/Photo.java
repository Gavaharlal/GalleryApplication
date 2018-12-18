package com.example.comp.imagelist.adapter;

import android.graphics.Bitmap;


public class Photo {

    private String description;

    private String smallImgUrl;

    private String fullImgUrl;

    private Bitmap imageBitmap;

    Photo(String description, String smallImgUrl, String fullImgUrl, Bitmap imageBitmap) {
        if (description.equals("null")) {
            this.description = "Without description";
        } else {
            this.description = description;
        }
        this.smallImgUrl = smallImgUrl;
        this.fullImgUrl = fullImgUrl;
        this.imageBitmap = imageBitmap;
    }

    String getSmallImgUrl() {
        return smallImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getFullImgUrl() {
        return fullImgUrl;
    }

    Bitmap getImageBitmap() {
        return imageBitmap;
    }
}
