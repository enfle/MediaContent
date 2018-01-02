package com.enfle.android.mediacontent.cast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Cast;
import com.enfle.android.mediacontent.utils.PaletteTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by subhamtyagi on 20/12/17.
 */

public class CastRecyclerView implements RecyclerItemView {

    private Cast mCast;

    public CastRecyclerView(Cast cast) {
        mCast = cast;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder) {
        MovieHolder movieHolder = (MovieHolder) holder;
        movieHolder.bind(mCast);
    }

    public Cast getMovie() {
        return mCast;
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

        void bind(Cast cast) {
            mTitle.setText(cast.getName());
            Picasso.with(mBackdrop.getContext())
                    .load("http://image.tmdb.org/t/p/w500/" + cast.getProfilePath())
                    .transform(PaletteTransformation.instance())
                    .fit()
                    .into(mBackdrop);
        }
    }
}
