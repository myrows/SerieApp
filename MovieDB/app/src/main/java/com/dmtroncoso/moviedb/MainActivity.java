package com.dmtroncoso.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmtroncoso.moviedb.data.local.PopularRoom;
import com.dmtroncoso.moviedb.model.popular.Result;
import com.dmtroncoso.moviedb.recyclerView.popular.SerieFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements SerieFragment.OnListFragmentInteractionListener {

    private FlowingDrawer fDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fDrawer = findViewById(R.id.drawerlayout);
        fDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);


        setupToolbar();
        setupMenu();

    }

    @Override
    public void onListFragmentInteraction(PopularRoom item) {

    }

    public void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        toolbar.setNavigationOnClickListener((v) -> {
            fDrawer.toggleMenu();
        });
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (fDrawer.isMenuVisible()) {
            fDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

}
