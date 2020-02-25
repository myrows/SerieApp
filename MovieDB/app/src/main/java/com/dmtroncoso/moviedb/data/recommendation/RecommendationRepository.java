package com.dmtroncoso.moviedb.data.recommendation;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.model.recommendation.Result;
import com.dmtroncoso.moviedb.model.popular.Serie;
import com.dmtroncoso.moviedb.model.recommendation.Recommendation;
import com.dmtroncoso.moviedb.retrofit.MovieService;
import com.dmtroncoso.moviedb.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationRepository {

    LiveData<List<Result>> allRecommendation;
    MovieService movieService;
    int idTv = SharedPreferencesManager.getSomeIntValue("idTv");

    public RecommendationRepository() {
        movieService = ServiceGenerator.createService(MovieService.class);
        allRecommendation = getAllRecommendation(idTv);
    }

    public LiveData<List<Result>> getAllRecommendation(int tv_id) {
        final MutableLiveData<List<Result>> dataRecommendation = new MutableLiveData<>();

        Call<Recommendation> call = movieService.getRecommendationsById(tv_id);
        call.enqueue(new Callback<Recommendation>() {
            @Override
            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
                if (response.isSuccessful()) {
                    dataRecommendation.setValue(response.body().getResults());
                } else {
                    Toast.makeText(MyApp.getContext(), "No se ha podido obtener resultados de la api", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Recommendation> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return dataRecommendation;
    }
}

