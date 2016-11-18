package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.bean.MovieListResponseDto;
import com.km2labs.mediacontent.common.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.common.ui.mvp.NetworkPresenter;
import com.km2labs.mediacontent.common.utils.CollectionUtils;
import com.km2labs.mediacontent.common.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public class SimilarMoviePresenter extends NetworkPresenter<SimilarMovieContract.View>
        implements SimilarMovieContract.Presenter {

    private MovieService mMovieService;

    public SimilarMoviePresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    protected void handleError(Throwable throwable) {

    }

    @Override
    public void getSimilarMovies(Integer movieId) {
        Subscription subscription = mMovieService.getSimilarMovies(movieId).flatMap(this::getViewItemObservable)
                .compose(RxUtils.applyMainIOSchedulers())
                .subscribe(this::onRequestCompleted, this::onError);

        addToSubscription(subscription);
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(MovieListResponseDto moviesListResponseData) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();

        if (moviesListResponseData != null) {
            List<Movie> movies = moviesListResponseData.getResults();
            Observable.from(movies).forEach(movie -> itemViews.add(new MovieGridViewItem(movie)));
        }

        return Observable.just(itemViews);
    }

    private void onRequestCompleted(List<RecyclerItemView> recyclerItemViews) {
        getView().onLoadingComplete(true);
        if (CollectionUtils.isEmpty(recyclerItemViews)) {
            getView().showEmptyScreen();
            return;
        }
        getView().showMovieList(recyclerItemViews);
    }
}
