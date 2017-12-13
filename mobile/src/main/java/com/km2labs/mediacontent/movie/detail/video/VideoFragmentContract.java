package com.km2labs.mediacontent.movie.detail.video;

import android.support.v7.widget.RecyclerView;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
    }
}
