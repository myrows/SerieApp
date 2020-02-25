package com.dmtroncoso.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dmtroncoso.moviedb.model.topRated.Result;
import com.dmtroncoso.moviedb.recyclerView.rated.RatedFragment;

public class TopRatedActivity extends AppCompatActivity implements RatedFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);
    }

    @Override
    public void onListFragmentInteraction(Result item) {

    }
}
