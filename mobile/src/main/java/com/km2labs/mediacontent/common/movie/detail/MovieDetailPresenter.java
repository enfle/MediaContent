package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.ui.mvp.NetworkPresenter;
import com.km2labs.mediacontent.common.utils.RxUtils;

import javax.inject.Inject;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailPresenter extends NetworkPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    private MovieService mMovieService;

    @Inject
    public MovieDetailPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    public void getMovieDetail(int movieId) {
        mMovieService.getMovieDetail(movieId, "videos,reviews,images")
                .compose(RxUtils.applyMainIOSchedulers())
                .subscribe(this::onRequestCompleted, this::onError);
    }

    private void onRequestCompleted(MovieDetailDto movieDetailDto) {
        getView().onMovieDetailReceived(movieDetailDto);
    }

    @Override
    protected void handleError(Throwable throwable) {

    }

    @Override
    public void onDestroy() {

    }
}
