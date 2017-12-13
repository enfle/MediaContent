package com.km2labs.mediacontent.movie.detail.overview;

import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
