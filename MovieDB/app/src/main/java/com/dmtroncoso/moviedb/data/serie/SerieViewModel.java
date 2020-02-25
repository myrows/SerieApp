package com.dmtroncoso.moviedb.data.serie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dmtroncoso.moviedb.data.local.PopularRoom;
import com.dmtroncoso.moviedb.data.local.network.Resource;
import com.dmtroncoso.moviedb.model.popular.Result;

import java.util.List;

public class SerieViewModel extends AndroidViewModel {
    SerieRepository serieRepository;
    LiveData<Resource<List<PopularRoom>>> series;

    public SerieViewModel(@NonNull Application application) {
        super(application);
        serieRepository = new SerieRepository();
    }

    public LiveData<Resource<List<PopularRoom>>> getSeries(){
        series = serieRepository.getPopularesDB();
        return series;
    }
}
