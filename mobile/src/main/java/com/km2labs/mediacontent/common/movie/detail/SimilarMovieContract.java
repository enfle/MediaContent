package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.framework.mvp.ILoadingView;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public interface SimilarMovieContract {

    interface View extends ILoadingView {

        void showMovieList(List<RecyclerItemView> recyclerItemViews);
    }

    interface Presenter extends INetworkPresenter<View> {

        void getSimilarMovies(Integer movieId);
    }
}
