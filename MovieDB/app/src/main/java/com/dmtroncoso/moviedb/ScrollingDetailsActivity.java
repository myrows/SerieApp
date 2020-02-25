package com.dmtroncoso.moviedb;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmtroncoso.moviedb.data.detail.DetailViewModel;
import com.dmtroncoso.moviedb.model.detail.Detail;
import com.dmtroncoso.moviedb.model.firebase.Favoritos;
import com.dmtroncoso.moviedb.recyclerView.recommendations.RecommendationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.dmtroncoso.moviedb.common.MyApp.getContext;

public class ScrollingDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public DetailViewModel detailViewModel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Favoritos favoritos;
    private GoogleSignInClient mGoogleSignIn;
    GoogleSignInAccount account;
    String doc;
    int idSerie;
    List<Favoritos> listFav;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView txtTitle = findViewById(R.id.textViewTitleDetails);
        final TextView txtNameAutor = findViewById(R.id.textViewAutorDetail);
        final TextView overView = findViewById(R.id.overView);
        final ImageView imageViewPoster = findViewById(R.id.imageViewPosterDetail);
        final ImageView imageViewAutor = findViewById(R.id.imageViewAutorDetail);
        final ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
        final RatingBar ratingBar = findViewById(R.id.ratingBarDetails);
        final CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        final FButton fButton = findViewById(R.id.fbutton);
        fab = findViewById(R.id.fab);

        //Info user signin
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignIn = GoogleSignIn.getClient(getContext(), gso);
        account = GoogleSignIn.getLastSignedInAccount(getContext());

        fButton.setOnClickListener(this);

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        detailViewModel.detail.observe(this, new Observer<Detail>() {
            @Override
            public void onChanged(Detail detail) {
                idSerie = detail.getId();

                txtTitle.setText(detail.getOriginalName());
                txtNameAutor.setText(detail.getCreatedBy().get(0).getName());
                overView.setText(detail.getOverview());
                toolbarLayout.setTitle(" ");

                //Objeto favoritos para añadir a firebase
                favoritos = new Favoritos(account.getId(),detail.getId() ,detail.getPosterPath());

                Glide
                        .with(ScrollingDetailsActivity.this)
                        .load(detail.getPosterPath())
                        .into(imageViewPoster);

                Glide
                        .with(ScrollingDetailsActivity.this)
                        .load(detail.getCreatedBy().get(0).getProfilePath())
                        .apply(RequestOptions.bitmapTransform(new CropCircleTransformation()))
                        .into(imageViewAutor);

                Glide
                        .with(ScrollingDetailsActivity.this)
                        .load(detail.getBackdropPath())
                        .into(imageViewBackground);


                double d = detail.getVoteAverage();
                float f = (float) d;
                ratingBar.setRating(f);

                db.collection("Favoritos")
                        .whereEqualTo("idSerie", idSerie)
                        .get()
                        .addOnCompleteListener(task -> {
                            listFav= task.getResult().toObjects(Favoritos.class);

                            if(listFav.size() != 0){
                                fab.setImageResource(R.drawable.ic_favorite_red_24dp);
                            }else if(listFav.size() == 0){
                                fab.setImageResource(R.drawable.ic_favorite_white_24dp);
                            }
                        });

                fab.setOnClickListener(view -> {

                    db.collection("Favoritos")
                            .whereEqualTo("idSerie", idSerie)
                            .get()
                            .addOnCompleteListener(task -> {
                                if(task.getResult().getDocumentChanges().size() != 0) {
                                    doc = task.getResult().getDocumentChanges().get(0).getDocument().getId();
                                }else{

                                }
                                listFav= task.getResult().toObjects(Favoritos.class);
                            });

            if(listFav.size() != 0 && doc != null) {
                //Delete serie que ya está favorita
                db.collection("Favoritos").document(doc).delete();
                fab.setImageResource(R.drawable.ic_favorite_white_24dp);


            }else if (listFav.size() == 0){
                //Add to firebase collection "favoritos"
                db.collection("Favoritos").add(favoritos).addOnSuccessListener(documentReference -> {
                    Log.d(TAG,"Favoritos added : " + documentReference.getId());
                }).addOnFailureListener(e -> Log.d(TAG,"Favoritos error", e));
                fab.setImageResource(R.drawable.ic_favorite_red_24dp);
            }

                });
            }
        });



        //Modify favorite color


        //FButton de favoritos

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ScrollingDetailsActivity.this, RecommendationActivity.class);
        startActivity(intent);
    }
}
