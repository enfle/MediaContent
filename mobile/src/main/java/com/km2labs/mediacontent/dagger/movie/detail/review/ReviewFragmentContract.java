package com.km2labs.mediacontent.dagger.movie.detail.review;

import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.core.mvp.view.ILoadingView;
import com.km2labs.mediacontent.core.mvp.view.INetworkView;
import com.km2labs.mediacontent.core.mvp.presenter.NetworkPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface ReviewFragmentContract {

    interface View extends ILoadingView {
        void showMovieReview(List<RecyclerItemView> reviewList);
    }

    interface Presenter extends NetworkPresenter<View> {
        void loadMovieReview(Integer movieId);
    }
}
