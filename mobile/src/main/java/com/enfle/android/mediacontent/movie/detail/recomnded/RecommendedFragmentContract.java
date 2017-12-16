package com.enfle.android.mediacontent.movie.detail.recomnded;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.mvp.ILoadingView;
import com.enfle.android.mediacontent.mvp.INetworkPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface RecommendedFragmentContract {

    interface View extends ILoadingView {
        void showMovieList(List<RecyclerItemView> reviewList);
    }

    interface Presenter extends INetworkPresenter<View> {
        void getRecommendedMovies(Integer movieId);
    }
}
