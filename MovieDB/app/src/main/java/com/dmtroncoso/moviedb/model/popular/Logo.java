package com.dmtroncoso.moviedb.model.popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logo {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("aspect_ratio")
    @Expose
    private Integer aspectRatio;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
