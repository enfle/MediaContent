package com.km2labs.mediacontent.common.movie.overview;

import com.km2labs.framework.mvp.ILoadingView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public interface OverviewFragmentContract {

    interface View extends ILoadingView {

        void onMovieDetailLoaded(MovieDetailDto movieDetailDto);
    }

    interface Presenter extends INetworkPresenter<View> {

        void getMovieDetail(Integer movieId);
    }
}
