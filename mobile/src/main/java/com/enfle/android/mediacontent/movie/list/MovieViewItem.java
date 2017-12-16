package com.enfle.android.mediacontent.movie.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Movie;
import com.enfle.android.mediacontent.utils.PaletteTransformation;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieViewItem implements RecyclerItemView {


    private Movie mMovie;

    public MovieViewItem(Movie movie) {
        mMovie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
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

        @BindView(R.id.subtitle)
        TextView mSubtitle;

        @BindView(R.id.rating_text)
        TextView mRatingText;

        @BindView(R.id.progressBar)
        ProgressBar mProgressBar;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            mTitle.setText(movie.getTitle());
            mSubtitle.setText(movie.getOverview());
            mRatingText.setText(String.format(Locale.US, "%3.1f", movie.getVoteAverage()));
            mProgressBar.setProgress((movie.getVoteAverage().intValue()));
            Picasso.with(mBackdrop.getContext())
                    .load("http://image.tmdb.org/t/p/w500/" + movie.getBackdropPath())
                    .transform(PaletteTransformation.instance())
                    .fit()
                    .into(mBackdrop);
        }
    }
}
