package com.dmtroncoso.moviedb.model.firebase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoritos")
public class Favoritos {

    @PrimaryKey
    private int idSerie;

    String url = "https://image.tmdb.org/t/p/w500";

    private String uid;
    private String poster;

    public Favoritos() {
    }

    public Favoritos(String uid, int idSerie, String poster) {
        this.uid = uid;
        this.idSerie = idSerie;
        this.poster = poster;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
