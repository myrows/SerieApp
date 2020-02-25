package com.dmtroncoso.moviedb.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "popular")
public class PopularRoom {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
