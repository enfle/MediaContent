package com.km2labs.mediacontent.loaders.movie.detail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.Poster;
import com.km2labs.mediacontent.common.movie.bean.Video;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : Subham Tyagi
 * Created on :  16/09/16.
 */

public class VideoRecyclerItemView implements RecyclerItemView {

    private Video mVideo;

    private Poster mBackdrop;

    public VideoRecyclerItemView(Video video, Poster backdrop) {
        mVideo = video;
        mBackdrop = backdrop;
    }

    public VideoRecyclerItemView(Video video) {
        mVideo = video;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder) {
        ((ViewHolder) holder).bind();
    }

    public Video getVideo() {
        return mVideo;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.backdrop)
        ImageView mBackdropImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            // Initialize placeholder drawable once
            Context context = mBackdropImage.getContext();
            Drawable mPlaceholderDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_placeholder_images, null);
            Picasso.with(context)
                    .load("http://image.tmdb.org/t/p/w185/" + mBackdrop.getFilePath())
                    .placeholder(mPlaceholderDrawable)
                    .fit()
                    .into(mBackdropImage);
        }
    }
}
