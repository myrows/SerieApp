package com.dmtroncoso.moviedb.recyclerView.recommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.model.recommendation.Result;
import com.dmtroncoso.moviedb.recyclerView.recommendations.RecomendationFragment;


public class RecommendationActivity extends AppCompatActivity implements RecomendationFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
    }

    @Override
    public void onListFragmentInteraction(Result item) {

    }
}
