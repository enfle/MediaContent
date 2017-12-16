package com.enfle.android.mediacontent.movie.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Movie;
import com.enfle.android.mediacontent.utils.PaletteTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieGridViewItem implements RecyclerItemView {


    private Movie mMovie;

    public MovieGridViewItem(Movie movie) {
        mMovie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder) {
        MovieHolder movieHolder = (MovieHolder) holder;
        movieHolder.bind(mMovie);
    }

    public Movie getMovie() {
        return mMovie;
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.backdrop)
        ImageView mBackdrop;

        @BindView(R.id.title)
        TextView mTitle;


        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            mTitle.setText(movie.getTitle());
            Picasso.with(mBackdrop.getContext())
                    .load("http://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                    .transform(PaletteTransformation.instance())
                    .fit()
                    .into(mBackdrop);
        }
    }
}
