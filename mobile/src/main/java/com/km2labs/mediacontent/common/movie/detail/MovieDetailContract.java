package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.core.mvp.view.ILoadingView;
import com.km2labs.mediacontent.core.mvp.presenter.NetworkPresenter;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public interface MovieDetailContract {

    interface Presenter extends NetworkPresenter<View> {
        void getMovieDetail(int movieId);
    }

    interface View extends ILoadingView {
        void onMovieDetailReceived(MovieDetailDto movieDetailDto);
    }
}
