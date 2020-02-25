package com.dmtroncoso.moviedb.recyclerView.popular;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.ScrollingDetailsActivity;
import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.data.local.PopularRoom;
import com.dmtroncoso.moviedb.data.local.network.Resource;
import com.dmtroncoso.moviedb.model.popular.Result;
import com.dmtroncoso.moviedb.recyclerView.popular.SerieFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySerieRecyclerViewAdapter extends RecyclerView.Adapter<MySerieRecyclerViewAdapter.ViewHolder> {

    private Resource<List<PopularRoom>> mValues;
    private final Resource<List<PopularRoom>> listSeriesFilter;
    private final OnListFragmentInteractionListener mListener;
    Context ctx;

    public MySerieRecyclerViewAdapter(Resource<List<PopularRoom>> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        listSeriesFilter = items;
        //listSeriesFilter = new ArrayList<>(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serie, parent, false);
        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null) {

            holder.mItem = mValues.data.get(position);

            holder.cardView.setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.fade_scale_animation));
            holder.cardView.setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.fade_transition_animation));

            String posterPath = "https://image.tmdb.org/t/p/w500"+holder.mItem.getPosterPath();

            Glide
                    .with(ctx)
                    .load("https://image.tmdb.org/t/p/w500"+holder.mItem.getPosterPath())
                    .into(holder.imageViewSerie);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                    SharedPreferencesManager.setSomeIntValue("idTv", holder.mItem.getId());
                    //serieRepository.setIdTv(holder.mItem.getId());
                    //serieViewModel.setidTv(holder.mItem.getId());
                    Intent intent = new Intent(ctx, ScrollingDetailsActivity.class);
                    intent.putExtra("idTv", holder.mItem.getId());
                    ctx.startActivity(intent);
                }
            }
        });
    }

    public void setData(Resource<List<PopularRoom>> resultList){
        this.mValues = resultList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mValues.data != null)
            return mValues.data.size();
        else{
            return 0;
        }
    }

    /*@Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Result> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(listSeriesFilter);
            }else{
                for (Result result : listSeriesFilter){
                    if(result.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(result);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mValues.clear();
            mValues.addAll((Collection<? extends Result>) results.values);
            notifyDataSetChanged();
        }
    };
    */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewSerie;
        public final CardView cardView;

        public PopularRoom mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewSerie = view.findViewById(R.id.imageViewSerie);
            cardView = view.findViewById(R.id.cardView);

        }

    }
}
