package com.dmtroncoso.moviedb.model.recommendation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logo {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("aspect_ratio")
    @Expose
    private Double aspectRatio;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
