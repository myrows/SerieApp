package com.dmtroncoso.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://www.youtube.com/watch?v=E4rrh_WnKMw
public class SeasonActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        expandableListView = findViewById(R.id.expandable_listview);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

    }
}
