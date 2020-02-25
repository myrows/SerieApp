package com.dmtroncoso.moviedb.data.recommendation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.model.recommendation.Result;

import java.util.List;

public class RecommendationViewModel extends AndroidViewModel {
    RecommendationRepository recommendationRepository;
    LiveData<List<Result>> recommendations;
    int idTv = SharedPreferencesManager.getSomeIntValue("idTv");

    public RecommendationViewModel(@NonNull Application application) {
        super(application);

        recommendationRepository = new RecommendationRepository();
        recommendations = recommendationRepository.getAllRecommendation(idTv);
    }

    public  LiveData<List<Result>> getRecommendations(){
        return recommendations;
    }
}
