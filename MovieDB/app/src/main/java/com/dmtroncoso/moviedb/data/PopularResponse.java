package com.dmtroncoso.moviedb.data;

import com.dmtroncoso.moviedb.data.local.PopularRoom;
import com.dmtroncoso.moviedb.model.popular.Result;

import java.util.List;

public class PopularResponse {
    private List<PopularRoom> results;

    public List<PopularRoom> getResults(){
        return results;
    }

    public void setResults(List<PopularRoom> results){
        this.results = results;
    }
}
