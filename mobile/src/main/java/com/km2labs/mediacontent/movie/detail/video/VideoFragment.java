package com.km2labs.mediacontent.movie.detail.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.youtube.player.YouTubeIntents;
import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;
import com.km2labs.mediacontent.beans.Video;

import java.util.List;


/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class VideoFragment extends RecyclerViewFragment<VideoFragmentContract.View, VideoFragmentContract.Presenter> implements VideoFragmentContract.View {

    public static final String ARG_MOVIE_ID = "Args:Fragment:Movie:ID";


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewAttached(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onViewDetached();
    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.GRID_LAYOUT_MANAGER;
    }

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected void onLoadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        Integer moviId = bundle.getInt(ARG_MOVIE_ID);
        mPresenter.loadVideos(moviId);
    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        VideoRecyclerView item = mAdapter.getItem(position);
        Video video = item.getVideo();
        Intent intent;
        if (YouTubeIntents.canResolvePlayVideoIntent(getContext())) {
            intent = YouTubeIntents.createPlayVideoIntentWithOptions(getContext(), video.getKey(), true, false);
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https:www.youtube.com/watch?v=" + video.getKey()));
        }
        startActivity(intent);
    }

    @Override
    public void showVideoList(List<RecyclerItemView> videos) {
        addItems(videos);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void onError() {
    }
}
