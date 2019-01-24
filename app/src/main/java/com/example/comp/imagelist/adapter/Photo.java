package com.example.comp.imagelist.adapter;

import android.graphics.Bitmap;


public class Photo {

    private String description;

    private String fullImgUrl;

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    private String smallImgUrl;

    private Bitmap smallImageBitmap;

    public Photo(String description, String fullImgUrl, Bitmap imageBitmap) {
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
