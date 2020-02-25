package com.dmtroncoso.moviedb.data.detail;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.model.detail.Detail;
import com.dmtroncoso.moviedb.model.season.Episode;
import com.dmtroncoso.moviedb.model.season.Season;
import com.dmtroncoso.moviedb.retrofit.MovieService;
import com.dmtroncoso.moviedb.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {

    LiveData<Detail> detail;
    LiveData<List<Episode>> season;
    MovieService movieService;
    int idTv;
    int seasonNumber;

    public DetailRepository() {
        movieService = ServiceGenerator.createServiceDetail(MovieService.class);
        idTv = SharedPreferencesManager.getSomeIntValue("idTv");
        detail = getDetail(idTv);
        season = getSeason(idTv, seasonNumber);
    }

    public LiveData<Detail> getDetail(int idTv){
        final MutableLiveData<Detail> dataDetail = new MutableLiveData<>();

        Call<Detail> call = movieService.getDetailById(idTv);
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if(response.isSuccessful()){
                    dataDetail.setValue(response.body());
                    detail = dataDetail;
                }else{
                    Toast.makeText(MyApp.getContext(), "No se ha podido obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return dataDetail;
    }

    public LiveData<List<Episode>> getSeason(int idTv, int seasonNumber){
        final MutableLiveData<List<Episode>> dataEpisode = new MutableLiveData<>();

        Call<Season> call = movieService.getSeasonBySerieId(idTv, seasonNumber);
        call.enqueue(new Callback<Season>() {
            @Override
            public void onResponse(Call<Season> call, Response<Season> response) {
                if(response.isSuccessful()){
                    dataEpisode.setValue(response.body().getEpisodes());
                    season = dataEpisode;
                }else{
                    Toast.makeText(MyApp.getContext(), "No se ha podido obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Season> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return dataEpisode;
    }
}
