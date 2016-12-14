package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.framework.mvp.ILoadingView;

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
