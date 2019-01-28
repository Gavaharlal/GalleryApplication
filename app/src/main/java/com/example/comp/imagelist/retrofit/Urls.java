package com.example.comp.imagelist.retrofit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties
public class Urls {

    @JsonProperty("small")
    private String smallUrl;

    @JsonProperty("full")
    private String fullUrl;

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }
}
