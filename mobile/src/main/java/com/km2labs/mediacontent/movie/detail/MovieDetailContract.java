package com.km2labs.mediacontent.movie.detail;

import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
