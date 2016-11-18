package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public interface OverviewFragmentContract {

    interface View extends ILoadingView {

        void onMovieDetailLoaded(MovieDetailDto movieDetailDto);
    }

    interface Presenter extends IPresenter<View> {

        void getMovieDetail(Integer movieId);
    }
}
