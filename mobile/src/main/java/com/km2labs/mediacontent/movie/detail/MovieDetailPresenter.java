package com.km2labs.mediacontent.movie.detail;

import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.mvp.BaseNetworkPresenter;
import com.km2labs.mediacontent.service.MovieService;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailPresenter extends BaseNetworkPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    private MovieService mMovieService;

    private int mMovieId;

    @Inject
    public MovieDetailPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    public void getMovieDetail(int movieId) {
        mMovieId = movieId;
        startRequest();
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        getView().onMovieDetailReceived((MovieDetailDto) data);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return null;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return mMovieService.getMovieDetail(mMovieId, "videos,reviews,images");
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }

    @Override
    public void onDestroy() {

    }

}