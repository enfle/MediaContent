package com.enfle.android.mediacontent.movie.detail.overview;

import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.mvp.ILoadingView;
import com.enfle.android.mediacontent.mvp.INetworkPresenter;

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
