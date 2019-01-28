package com.example.comp.imagelist.adapter;

import android.os.Parcel;
import android.os.Parcelable;


public class Photo implements Parcelable {

    private String id;

    private String smallUrl;
    private String fullUrl;
    private String description;
    public Photo(String id, String smallUrl, String fullUrl, String description) {
        this.id = id;
        this.smallUrl = smallUrl;
        this.fullUrl = fullUrl;
        if (description == null) {
            this.description = "Without description";
        } else {
            this.description = description;
        }
    }

    private Photo(Parcel in) {
        id = in.readString();
        smallUrl = in.readString();
        fullUrl = in.readString();
        description = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(smallUrl);
        dest.writeString(fullUrl);
        dest.writeString(description);
    }


    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}