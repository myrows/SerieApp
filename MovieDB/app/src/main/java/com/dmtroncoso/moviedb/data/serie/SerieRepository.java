package com.dmtroncoso.moviedb.data.serie;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.data.local.MovieRoomDatabase;
import com.dmtroncoso.moviedb.data.local.PopularRoom;
import com.dmtroncoso.moviedb.data.local.dao.MovieDao;
import com.dmtroncoso.moviedb.data.local.network.NetworkBoundResource;
import com.dmtroncoso.moviedb.data.local.network.Resource;
import com.dmtroncoso.moviedb.data.PopularResponse;
import com.dmtroncoso.moviedb.model.popular.Result;
import com.dmtroncoso.moviedb.model.popular.Serie;
import com.dmtroncoso.moviedb.retrofit.MovieService;
import com.dmtroncoso.moviedb.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// https://github.com/iammert/AndroidArchitecture/blob/master/app/src/main/java/iammert/com/androidarchitecture/data/MovieRepository.java

public class SerieRepository {

    LiveData<List<Result>> allSeries;
    MovieService movieService;
    MovieRoomDatabase movieRoomDatabase;
    MovieDao movieDao;

    public SerieRepository() {
        movieService = ServiceGenerator.createService(MovieService.class);
        allSeries = getPopularesAPI();
        movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_popular"
        ).build();

        movieDao = movieRoomDatabase.getPopularDao();
    }

    public LiveData<List<Result>> getPopularesAPI() {
        final MutableLiveData<List<Result>> dataSerie = new MutableLiveData<>();

        Call<Serie> call = movieService.getSeries();
        call.enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if(response.isSuccessful()){
                    dataSerie.setValue(response.body().getResults());
                }else{
                    Toast.makeText(MyApp.getContext(), "No se ha podido obtener resultados de la api", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return dataSerie;
    }

    public LiveData<Resource<List<PopularRoom>>> getPopularesDB(){
        // Tipo que devuelve  Room (BD local),  Tipo  que devuelve la API con Retrofit
        return new NetworkBoundResource<List<PopularRoom>, PopularResponse>(){

            @Override
            protected void saveCallResult(@NonNull PopularResponse item) {
                movieDao.savePopular(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<PopularRoom>> loadFromDb() {
                return movieDao.loadSeries();
            }

            @NonNull
            @Override
            protected Call<PopularResponse> createCall() {
                return movieService.getSeriesPopularResponse();
            }
        }.getAsLiveData();
    }
}
