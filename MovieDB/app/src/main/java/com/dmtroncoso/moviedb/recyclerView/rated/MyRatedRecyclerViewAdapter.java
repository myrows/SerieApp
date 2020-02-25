package com.dmtroncoso.moviedb.recyclerView.rated;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmtroncoso.moviedb.R;
import com.dmtroncoso.moviedb.model.topRated.Result;
import com.dmtroncoso.moviedb.recyclerView.rated.RatedFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyRatedRecyclerViewAdapter extends RecyclerView.Adapter<MyRatedRecyclerViewAdapter.ViewHolder> {

    private List<Result> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context ctx;

    public MyRatedRecyclerViewAdapter(List<Result> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rated, parent, false);

        ctx = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues != null){
            holder.mItem = mValues.get(position);
            Glide
                    .with(ctx)
                    .load(holder.mItem.getPosterPath())
                    .into(holder.imageViewRated);

        }



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void setData(List<Result> listRated){
        this.mValues = listRated;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mValues != null){
            return mValues.size();
        }else
            return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewRated;
        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewRated = view.findViewById(R.id.imageViewRated);

        }

    }
}
