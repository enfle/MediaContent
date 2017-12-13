package com.km2labs.mediacontent.movie.detail.recomnded;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
