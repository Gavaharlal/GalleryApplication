package com.example.comp.imagelist.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class ModelPhoto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("urls")
    private Urls urls;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Urls getUrls() {
        return urls;
    }

}
