package com.km2labs.mediacontent.movie.detail.recomnded;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.beans.Movie;
import com.km2labs.mediacontent.beans.MovieListResponseDto;
import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.mvp.BaseNetworkPresenter;
import com.km2labs.mediacontent.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class RecommendedFragmentPresenter extends BaseNetworkPresenter<RecommendedFragmentContract.View>
        implements RecommendedFragmentContract.Presenter {

    private MovieService mMovieService;
    private int mMovieId;

    @Inject
    public RecommendedFragmentPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }


    @Override
    public void getRecommendedMovies(Integer movieId) {
        startRequest();
        mMovieId = movieId;
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(MovieListResponseDto reviewDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        if (reviewDto != null) {
            List<Movie> movies = reviewDto.getResults();
            Observable.fromIterable(movies).forEach(review -> itemViews.add(new MovieGridViewItem(review)));
        }
        return Observable.just(itemViews);
    }

    @Override
    protected <D> Observable<?> transformResponseData(D data) {
        return this.getViewItemObservable((MovieListResponseDto) data);
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        getView().onLoadingComplete(true);
        getView().showMovieList((List<RecyclerItemView>) data);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return false;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return mMovieService.getRecommendedMovies(mMovieId);
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }
}
