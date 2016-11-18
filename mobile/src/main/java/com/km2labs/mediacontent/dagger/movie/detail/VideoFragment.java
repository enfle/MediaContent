package com.km2labs.mediacontent.dagger.movie.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.common.movie.bean.Backdrop;
import com.km2labs.mediacontent.common.movie.bean.Video;
import com.km2labs.mediacontent.common.movie.bean.Videos;
import com.km2labs.mediacontent.common.movie.detail.VideoFragmentContract;
import com.km2labs.mediacontent.common.ui.adapter.ItemizedRecyclerAdapter;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.dagger.core.ui.fragment.RecyclerViewFragment;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivitySubcomponentBuilders;

import org.parceler.Parcels;

import java.util.List;

import rx.Observable;

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
        Observable.from(videos.getVideos()).forEach(video -> {
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
    public void onError() {

    }

    @Override
    protected void injectMembers(ActivitySubcomponentBuilders activitySubcomponentBuilders) {

    }
}
