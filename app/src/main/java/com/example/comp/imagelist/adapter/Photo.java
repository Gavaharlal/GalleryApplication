package com.example.comp.imagelist.adapter;

import android.graphics.Bitmap;


public class Photo {

    private String description;

    private String fullImgUrl;

    private Bitmap smallImageBitmap;

    Photo(String description, String fullImgUrl, Bitmap imageBitmap) {
        if (description.equals("null")) {
            this.description = "Without description";
        } else {
            this.description = description;
        }
        this.fullImgUrl = fullImgUrl;
        this.smallImageBitmap = imageBitmap;
    }


    public String getDescription() {
        return description;
    }

    public String getFullImgUrl() {
        return fullImgUrl;
    }

    public Bitmap getSmallImageBitmap() {
        return smallImageBitmap;
    }

}
