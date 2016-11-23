package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.framework.mvp.ILoadingView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public interface MovieDetailContract {

    interface Presenter extends INetworkPresenter<View> {
        void getMovieDetail(int movieId);
    }

    interface View extends ILoadingView {
        void onMovieDetailReceived(MovieDetailDto movieDetailDto);
    }
}
