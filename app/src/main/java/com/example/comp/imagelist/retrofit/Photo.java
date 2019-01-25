package com.example.comp.imagelist.retrofit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Photo {

    @JsonCreator
    public Photo(@JsonProperty("description") String description,
                 @JsonProperty("urls") Urls urls) {
        this.description = description;
        this.urls = urls;
    }

    @JsonProperty("id")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("urls")
    private Urls urls;

    public String getDescription() {
        return description;
    }

    public Urls getUrls() {
        return urls;
    }

}
