package com.km2labs.mediacontent.dagger.movie.detail.video;

import android.support.v7.widget.RecyclerView;

import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;

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

    interface Presenter extends IPresenter<View> {

        void loadVideos(Integer movieId);
    }
}
