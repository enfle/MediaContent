package com.enfle.android.mediacontent.movie.detail.video;

import android.support.v7.widget.RecyclerView;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Backdrop;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.mvp.ILoadingView;
import com.enfle.android.mediacontent.mvp.INetworkPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface VideoFragmentContract {

    interface View extends ILoadingView {

        void showVideoList(List<RecyclerItemView> videos);

        void showErrorMessage();

        RecyclerView getRecyclerView();
    }

    interface Presenter extends INetworkPresenter<View> {

        void loadVideos(Integer movieId);

        List<Backdrop> getBackdropList(MovieDetailDto movieDetailDto);

        void loadVideos(MovieDetailDto movieDetailDto);
    }
}
