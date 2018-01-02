package com.enfle.android.mediacontent.movie.detail.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.base.fragments.LayoutManagerType;
import com.enfle.android.mediacontent.base.fragments.RecyclerViewFragment;
import com.enfle.android.mediacontent.beans.Backdrop;
import com.enfle.android.mediacontent.beans.Images;
import com.enfle.android.mediacontent.beans.Video;
import com.enfle.android.mediacontent.beans.Videos;
import com.google.android.youtube.player.YouTubeIntents;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class VideoFragment extends RecyclerViewFragment {

    public static final String ARG_MOVIE_VIDEOS = "Args:Fragment:Movie:Videos";
    public static final String ARG_MOVIE_IMAGES = "Args:Fragment:Movie:Images";

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();

        Videos videos = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE_VIDEOS));
        List<Video> videoList = videos.getVideos();

        Images images = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE_IMAGES));
        List<Backdrop> backdrops = images.getBackdrops();
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            Backdrop backdrop;
            if (i < backdrops.size()) {
                backdrop = backdrops.get(i);
            } else {
                backdrop = backdrops.get(0);
            }
            itemViews.add(new VideoRecyclerView(video, backdrop));
        }
        showVideoList(itemViews);
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

    private void showVideoList(List<RecyclerItemView> videos) {
        addItems(videos);
    }
}
