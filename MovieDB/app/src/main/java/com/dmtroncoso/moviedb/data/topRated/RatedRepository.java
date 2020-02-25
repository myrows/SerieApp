package com.dmtroncoso.moviedb.data.topRated;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.model.topRated.PageRated;
import com.dmtroncoso.moviedb.model.topRated.Result;
import com.dmtroncoso.moviedb.retrofit.MovieService;
import com.dmtroncoso.moviedb.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatedRepository {
    LiveData<List<Result>> listRated;
    MovieService movieService;

    public RatedRepository() {
        movieService = ServiceGenerator.createService(MovieService.class);
        listRated = getAllRated();
    }

    public LiveData<List<Result>> getAllRated(){
        MutableLiveData<List<Result>> dataRated = new MutableLiveData<>();

        Call<PageRated> call = movieService.getAllTopRated();
        call.enqueue(new Callback<PageRated>() {
            @Override
            public void onResponse(Call<PageRated> call, Response<PageRated> response) {
                if (response.isSuccessful()){
                    dataRated.setValue(response.body().getResults());
                }else{
                    Toast.makeText(MyApp.getContext(), "No se ha podido devolver los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageRated> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return dataRated;
    }


}
