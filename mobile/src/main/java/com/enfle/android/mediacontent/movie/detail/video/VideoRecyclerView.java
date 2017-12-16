package com.enfle.android.mediacontent.movie.detail.video;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Backdrop;
import com.enfle.android.mediacontent.beans.Video;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : Subham Tyagi
 * Created on :  16/09/16.
 */

public class VideoRecyclerView implements RecyclerItemView {

    private Video mVideo;

    private Backdrop mBackdrop;

    public VideoRecyclerView(Video video, Backdrop backdrop) {
        mVideo = video;
        mBackdrop = backdrop;
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

        public void bind() {
            Picasso.with(mBackdropImage.getContext())
                    .load("http://image.tmdb.org/t/p/w500/" + mBackdrop.getFilePath())
                    .resize(150, 150)
                    .centerCrop()
                    .into(mBackdropImage);
        }
    }
}
