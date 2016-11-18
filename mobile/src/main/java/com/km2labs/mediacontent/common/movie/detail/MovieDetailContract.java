package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public interface MovieDetailContract {

    interface Presenter extends IPresenter<View> {
        void getMovieDetail(int movieId);
    }

    interface View extends ILoadingView {
        void onMovieDetailReceived(MovieDetailDto movieDetailDto);
    }
}
