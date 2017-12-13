package com.km2labs.mediacontent.movie.detail.reviews;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface ReviewFragmentContract {

    interface View extends ILoadingView {
        void showMovieReview(List<RecyclerItemView> reviewList);
    }

    interface Presenter extends INetworkPresenter<View> {
        void loadMovieReview(Integer movieId);
    }
}
