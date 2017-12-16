package com.enfle.android.mediacontent.movie.detail;

import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.mvp.ILoadingView;
import com.enfle.android.mediacontent.mvp.INetworkPresenter;

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
