package com.dmtroncoso.moviedb.data.favorite;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.data.local.MovieRoomDatabase;
import com.dmtroncoso.moviedb.data.local.dao.MovieDao;
import com.dmtroncoso.moviedb.data.local.network.NetworkBoundResource;
import com.dmtroncoso.moviedb.data.local.network.Resource;
import com.dmtroncoso.moviedb.model.FavoriteResponse;
import com.dmtroncoso.moviedb.model.firebase.Favoritos;
import com.dmtroncoso.moviedb.retrofit.MovieService;
import com.dmtroncoso.moviedb.retrofit.ServiceGenerator;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import retrofit2.Call;

public class FavoriteRepository {
    LiveData<List<Favoritos>> favoritos;
    FirebaseFirestore db;
    MovieRoomDatabase movieRoomDatabase;
    MovieDao movieDao;

    public FavoriteRepository() {
        db = FirebaseFirestore.getInstance();
        favoritos = getAllFavoritos();
        movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_favoritos"
        ).build();
        //movieDao = movieRoomDatabase.getFavoritosDao();
    }

    public LiveData<List<Favoritos>> getAllFavoritos(){
        MutableLiveData<List<Favoritos>> dataFav = new MutableLiveData<>();

        db.collection("Favoritos")
                .get()
                .addOnCompleteListener(task -> {
                    dataFav.setValue(task.getResult().toObjects(Favoritos.class));

                });
        return dataFav;
    }

    /*public LiveData<Resource<List<Favoritos>>> getFavoritos(){
        // Tipo que devuelve  Room (BD local),  Tipo  que devuelve la API con Retrofit
        return new NetworkBoundResource<List<Favoritos>, FavoriteResponse>(){

            @Override
            protected void saveCallResult(@NonNull FavoriteResponse item) {
                movieDao.saveFavoritos(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Favoritos>> loadFromDb() {
                //los datos de la base de datos local
                return movieDao.loadFavoritos();
            }

            @NonNull
            @Override
            protected Call<FavoriteResponse> createCall() {
                // en caso de tener acceso a internet obtenemos los datos de la api remota
                return ;
            }
        }.getAsLiveData();
    }*/
}
