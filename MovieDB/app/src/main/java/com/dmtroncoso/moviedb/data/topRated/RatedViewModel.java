package com.dmtroncoso.moviedb.data.topRated;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dmtroncoso.moviedb.model.topRated.Result;

import java.util.List;

public class RatedViewModel extends AndroidViewModel {
    RatedRepository ratedRepository;
    LiveData<List<Result>> rated;

    public RatedViewModel(@NonNull Application application) {
        super(application);

        ratedRepository = new RatedRepository();
        rated = ratedRepository.getAllRated();
    }

    public LiveData<List<Result>> getRated(){
        return rated;
    }
}
