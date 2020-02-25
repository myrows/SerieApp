package com.dmtroncoso.moviedb.data.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dmtroncoso.moviedb.model.firebase.Favoritos;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    FavoriteRepository favoriteRepository;
    LiveData<List<Favoritos>> favoritos;


    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository();
        favoritos = favoriteRepository.getAllFavoritos();

    }

    public LiveData<List<Favoritos>> getFavoritos(){
        return favoritos;
    }
}
