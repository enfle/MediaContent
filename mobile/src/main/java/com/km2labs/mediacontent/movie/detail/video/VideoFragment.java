package com.km2labs.mediacontent.movie.detail.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.base.adapter.ItemizedRecyclerAdapter;
import com.km2labs.mediacontent.base.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;
import com.km2labs.mediacontent.beans.Backdrop;
import com.km2labs.mediacontent.beans.Video;
import com.km2labs.mediacontent.beans.Videos;

import org.parceler.Parcels;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class VideoFragment extends RecyclerViewFragment<VideoFragmentContract.View, VideoFragmentContract.Presenter> implements VideoFragmentContract.View {

    public static final String ARG_VIDEO_LIST = "Arg:Fragment:Movie:Video";
    public static final String ARG_BACKDROP_LIST = "Arg:Fragment:Movie:Backdrop";


    @Override
    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

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

        Videos videos = Parcels.unwrap((Parcelable) bundle.get(ARG_VIDEO_LIST));
        List<Backdrop> backdrops = Parcels.unwrap((Parcelable) bundle.get(ARG_BACKDROP_LIST));
        final int[] i = {0};
        Observable.fromIterable(videos.getVideos()).forEach(video -> {
            Backdrop backdrop = null;
            backdrop = backdrops.get(i[0]++);

            if (backdrops.size() <= i[0]) {
                i[0] = 0;
            }
            new VideoRecyclerView(video, backdrop);
        });
    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        VideoRecyclerView item = mAdapter.getItem(position);
        Video video = item.getVideo();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https:www.youtube.com/watch?v=" + video.getKey()));
        startActivity(intent);
    }

    @Override
    public void showVideoList(List<RecyclerItemView> videos) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return null;
    }

    @Override
    public void onLoadingStart() {

    }

    @Override
    public void onLoadingComplete(boolean success) {

    }

    @Override
    public void showEmptyScreen() {

    }

    @Override
    public void onNetworkError(String tag) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onAuthenticationError(String tag) {

    }

    @Override
    protected int getContentLayoutResId() {
        return 0;
    }
}
