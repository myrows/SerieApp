package com.dmtroncoso.moviedb.recyclerView.favorites;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.ScrollingDetailsActivity;
import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.model.firebase.Favoritos;
import com.dmtroncoso.moviedb.recyclerView.favorites.FavoriteFragment.OnListFragmentInteractionListener;

import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private List<Favoritos> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context ctx;

    public MyFavoriteRecyclerViewAdapter(List<Favoritos> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);

        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues != null){
            holder.mItem = mValues.get(position);

            Glide
                    .with(ctx)
                    .load(holder.mItem.getPoster())
                    .into(holder.imageViewFavorite);

            /*holder.waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    notifyDataSetChanged();
                    Toast.makeText(ctx, "Cargando imagen", Toast.LENGTH_SHORT).show();
                }
            });*/
        }



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    SharedPreferencesManager.setSomeIntValue("idTv", holder.mItem.getIdSerie());
                    Intent intent = new Intent(ctx, ScrollingDetailsActivity.class);
                    ctx.startActivity(intent);
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void setData (List<Favoritos> favList){
        this.mValues = favList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues != null){
            return mValues.size();
        }else{
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewFavorite;
        //public final WaveSwipeRefreshLayout waveSwipeRefreshLayout;

        public Favoritos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewFavorite = view.findViewById(R.id.imageViewFavorite);
            //waveSwipeRefreshLayout = view.findViewById(R.id.main_swipe);
        }

    }
}
