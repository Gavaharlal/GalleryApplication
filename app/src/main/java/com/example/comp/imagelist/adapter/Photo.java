package com.example.comp.imagelist.adapter;

public class Photo {

    private String description;

    private String smallImgUrl;

    private String fullImgUrl;

    Photo(String description, String small, String full) {
        this.description = description;
        this.smallImgUrl = small;
        this.fullImgUrl = full;
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
}
