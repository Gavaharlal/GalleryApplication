package com.example.comp.imagelist.adapter;

import android.graphics.Bitmap;


public class Photo {

    private String description;

    private String smallImgUrl;

    private String fullImgUrl;

    private Bitmap smallImageBitmap;

    private Bitmap fullImageBitmap;

    Photo(String description, String smallImgUrl, String fullImgUrl, Bitmap imageBitmap) {
        if (description.equals("null")) {
            this.description = "Without description";
        } else {
            this.description = description;
        }
        this.smallImgUrl = smallImgUrl;
        this.fullImgUrl = fullImgUrl;
        this.smallImageBitmap = imageBitmap;
    }


    String getDescription() {
        return description;
    }

    String getFullImgUrl() {
        return fullImgUrl;
    }

    Bitmap getSmallImageBitmap() {
        return smallImageBitmap;
    }

}
