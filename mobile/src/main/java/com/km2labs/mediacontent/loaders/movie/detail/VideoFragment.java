package com.km2labs.mediacontent.loaders.movie.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.youtube.player.YouTubeIntents;
import com.km2labs.framework.network.RetrofitHelper;
import com.km2labs.framework.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Video;
import com.km2labs.mediacontent.common.movie.video.VideoFragmentContract;
import com.km2labs.mediacontent.common.movie.video.VideoListPresenter;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.loaders.RecyclerViewFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class VideoFragment extends RecyclerViewFragment<VideoFragmentContract.View, VideoFragmentContract.Presenter> implements VideoFragmentContract.View {

    public static final String ARG_MOVIE_ID = "Arg:Fragment:Movie:Video";

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.GRID_LAYOUT_MANAGER;
    }

    @Override
    protected void onLoadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        Integer movieId = bundle.getInt(ARG_MOVIE_ID);
        mPresenter.loadVideos(movieId);
    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        VideoRecyclerItemView item = mAdapter.getItem(position);
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
    public void onError() {

    }

    @Override
    protected int getLoaderId() {
        return 10;
    }

    @Override
    protected VideoFragmentContract.View getMVPView() {
        return this;
    }

    @Override
    protected PresenterFactory<VideoFragmentContract.Presenter> getPresenterFactory() {
        return () -> new VideoListPresenter(RetrofitHelper.getRetrofitHelper()
                .createService(MovieService.class), new InMemoryCache());
    }

    @Override
    public void showVideoList(List<RecyclerItemView> videos) {
        super.addItems(videos);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
