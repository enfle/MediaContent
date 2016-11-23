package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.framework.cache.DataCache;
import com.km2labs.framework.network.BaseNetworkPresenter;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.bean.MovieListResponseDto;
import com.km2labs.mediacontent.common.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class RecommendedFragmentPresenter extends BaseNetworkPresenter<RecommendedFragmentContract.View>
        implements RecommendedFragmentContract.Presenter {

    private MovieService mMovieService;
    private int mMovieId;

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
            Observable.from(movies).forEach(review -> itemViews.add(new MovieGridViewItem(review)));
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
