package com.km2labs.mediacontent.movie.detail.similar;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
