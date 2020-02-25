package com.dmtroncoso.moviedb.recyclerView.recommendations;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.model.recommendation.Result;
import com.dmtroncoso.moviedb.recyclerView.recommendations.RecomendationFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyRecomendationRecyclerViewAdapter extends RecyclerView.Adapter<MyRecomendationRecyclerViewAdapter.ViewHolder> {

    List<Result> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context ctx;

    public MyRecomendationRecyclerViewAdapter(List<Result> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recomendation, parent, false);

        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null) {
            holder.mItem = mValues.get(position);

            Glide
                    .with(ctx)
                    .load(holder.mItem.getPosterPath())
                    .into(holder.imageR);
        }
        holder.mItem = mValues.get(position);

        Glide
                .with(ctx)
                .load(holder.mItem.getPosterPath())
                .into(holder.imageR);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {

                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    public void setData(List<Result> resultList){
        this.mValues = resultList;
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
        public final ImageView imageR;

        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageR = view.findViewById(R.id.imageViewR);

        }

    }
}
