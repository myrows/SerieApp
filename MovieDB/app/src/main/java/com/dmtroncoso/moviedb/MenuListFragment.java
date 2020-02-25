
package com.dmtroncoso.moviedb;

import android.content.Intent;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmtroncoso.moviedb.login.LoggingActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

    private ImageView ivMenuUserProfilePhoto;
    private TextView userName;
    private GoogleSignInClient mGoogleSignIn;
    GoogleSignInAccount account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignIn = GoogleSignIn.getClient(getContext(), gso);
        account = GoogleSignIn.getLastSignedInAccount(getContext());
        ivMenuUserProfilePhoto = view.findViewById(R.id.imageViewGoogle);
        userName = view.findViewById(R.id.userNameLogged);
        NavigationView vNavigation = view.findViewById(R.id.vNavigation);

        userName.setText(account.getGivenName());

        Glide
                .with(this)
                .load(account.getPhotoUrl())
                .apply(RequestOptions.bitmapTransform(new CropCircleTransformation()))
                .into(ivMenuUserProfilePhoto);

        vNavigation.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_popular:
                    Intent intentPopular = new Intent(getContext(), MainActivity.class);
                    startActivity(intentPopular);
                    break;
                case R.id.menu_rated:
                    Intent intentRated = new Intent(getContext(), TopRatedActivity.class);
                    startActivity(intentRated);
                    break;
                case R.id.menu_latest:
                    break;
                case R.id.menu_sesion:
                    mGoogleSignIn.signOut();
                    Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), LoggingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_info:
                    Intent intentInfo = new Intent(getContext(), InfoActivity.class);
                    startActivity(intentInfo);
                    break;
                case R.id.menu_fav:
                    Intent intentFav = new Intent(getContext(), FavoriteActivity.class);
                    startActivity(intentFav);
                    break;
            }
            return false;
        }) ;
        //setupHeader();
        return  view ;
    }

    private void setupHeader() {



    }

}
