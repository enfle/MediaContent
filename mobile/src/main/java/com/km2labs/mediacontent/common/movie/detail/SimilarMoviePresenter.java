package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.framework.cache.DataCache;
import com.km2labs.framework.network.BaseNetworkPresenter;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.bean.MovieListResponseDto;
import com.km2labs.mediacontent.common.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public class SimilarMoviePresenter extends BaseNetworkPresenter<SimilarMovieContract.View>
        implements SimilarMovieContract.Presenter {

    private MovieService mMovieService;

    private int mMovieId;

    public SimilarMoviePresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    public void getSimilarMovies(Integer movieId) {
        mMovieId = movieId;
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(MovieListResponseDto moviesListResponseData) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();

        if (moviesListResponseData != null) {
            List<Movie> movies = moviesListResponseData.getResults();
            Observable.from(movies).forEach(movie -> itemViews.add(new MovieGridViewItem(movie)));
        }

        return Observable.just(itemViews);
    }


    @Override
    protected <D> Observable<?> transformResponseData(D data) {
        return getViewItemObservable((MovieListResponseDto) data);
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        List<RecyclerItemView> recyclerItemViews = (List<RecyclerItemView>) data;
        getView().onLoadingComplete(true);
        if (CollectionUtils.isEmpty(recyclerItemViews)) {
            getView().showEmptyScreen();
            return;
        }
        getView().showMovieList(recyclerItemViews);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return false;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return mMovieService.getSimilarMovies(mMovieId);
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }
}
