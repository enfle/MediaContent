package com.km2labs.mediacontent.dagger.movie.detail.video;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.Backdrop;
import com.km2labs.mediacontent.common.movie.bean.Video;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
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
                    .fit()
                    .into(mBackdropImage);
        }
    }
}
