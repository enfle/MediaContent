package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter;
import com.km2labs.mediacontent.core.util.RxUtils;
import com.km2labs.mediacontent.dagger.core.scope.InMemory;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailPresenter extends AbsNetworkPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    private MovieService mMovieService;

    @Inject
    public MovieDetailPresenter(MovieService movieService, @InMemory DataCache dataCache) {
        mMovieService = movieService;
    }

    @Override
    public void getMovieDetail(int movieId) {
        mMovieService.getMovieDetail(movieId, "videos,reviews,images")
                .compose(RxUtils.applyMainIOSchedulers())
                .subscribe(this::onRequestCompleted, error->{});
    }

    private void onRequestCompleted(MovieDetailDto movieDetailDto) {
        getView().onMovieDetailReceived(movieDetailDto);
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {

    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return null;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return null;
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void retry(String tag) {

    }
}
