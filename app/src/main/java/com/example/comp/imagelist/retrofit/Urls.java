package com.example.comp.imagelist.retrofit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties
public class Urls {

    @JsonCreator
    public Urls(@JsonProperty("small") String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    @JsonProperty("small")
    private String smallUrl;
}
