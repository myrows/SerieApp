package com.dmtroncoso.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.dmtroncoso.moviedb.common.MyApp;
import com.dmtroncoso.moviedb.data.favorite.FavoriteViewModel;
import com.dmtroncoso.moviedb.model.firebase.Favoritos;
import com.dmtroncoso.moviedb.recyclerView.favorites.FavoriteFragment;
import com.dmtroncoso.moviedb.recyclerView.favorites.MyFavoriteRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class FavoriteActivity extends AppCompatActivity implements FavoriteFragment.OnListFragmentInteractionListener {

    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    FavoriteViewModel favoriteViewModel;
    List<Favoritos> favoritesList;
    MyFavoriteRecyclerViewAdapter myFavoriteRecyclerViewAdapter;
    FavoriteFragment.OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        waveSwipeRefreshLayout = findViewById(R.id.main_swipeFavorite);

        waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                waveSwipeRefreshLayout.setRefreshing(false);
            }

        });


        /*waveSwipeRefreshLayout = findViewById(R.id.main_swipeFavorite);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        mListener = FavoriteActivity.this;
        recyclerView = findViewById(R.id.listFav);

        waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFavorites();
                waveSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(FavoriteActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }

        });

        myFavoriteRecyclerViewAdapter = new MyFavoriteRecyclerViewAdapter(favoritesList, mListener);
        recyclerView.setAdapter(myFavoriteRecyclerViewAdapter);*/
    }

    /*public void loadFavorites(){
        favoriteViewModel.getFavoritos().observe(this, new Observer<List<Favoritos>>() {
            @Override
            public void onChanged(List<Favoritos> favoritos) {
                if(favoritos != null) {
                    favoritesList = favoritos;
                    myFavoriteRecyclerViewAdapter.setData(favoritesList);

                    //Refresh
                    waveSwipeRefreshLayout.setRefreshing(true);
                    recyclerView.setAdapter(myFavoriteRecyclerViewAdapter);
                }else{
                    Toast.makeText(FavoriteActivity.this, "No hay series nuevas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    @Override
    public void onListFragmentInteraction(Favoritos item) {

    }
}
