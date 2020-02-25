package com.dmtroncoso.moviedb.model;

import com.dmtroncoso.moviedb.model.firebase.Favoritos;

import java.util.List;

public class FavoriteResponse {
    private List<Favoritos> results;

    public List<Favoritos> getResults(){
        return results;
    }

    public void setResults(List<Favoritos> results){
        this.results = results;
    }
}
