package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public interface SimilarMovieContract {

    interface View extends ILoadingView {

        void showMovieList(List<RecyclerItemView> recyclerItemViews);
    }

    interface Presenter extends IPresenter<View> {

        void getSimilarMovies(Integer movieId);
    }
}
