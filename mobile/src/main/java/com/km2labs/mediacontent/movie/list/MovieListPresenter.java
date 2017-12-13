package com.km2labs.mediacontent.movie.list;

import android.support.v7.widget.RecyclerView;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.beans.Movie;
import com.km2labs.mediacontent.beans.MovieListResponseDto;
import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.dagger.scope.InMemoryCache;
import com.km2labs.mediacontent.mvp.BaseNetworkPresenter;
import com.km2labs.mediacontent.service.MovieService;
import com.km2labs.mediacontent.utils.CollectionUtils;
import com.km2labs.mediacontent.utils.PaginationTool;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
public class MovieListPresenter extends BaseNetworkPresenter<MovieListFragmentContract.View> implements MovieListFragmentContract.Presenter {

    private static final int LIMIT = 20;
    private MovieService mMovieService;

    private MovieListType mMovieListType;

    @Inject
    public MovieListPresenter(MovieService movieService, @InMemoryCache DataCache memoryCache) {
        super(memoryCache);
        mMovieService = movieService;
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        List<RecyclerItemView> recyclerItemViews = (List<RecyclerItemView>) data;
        if (CollectionUtils.isEmpty(recyclerItemViews)) {
            getView().showEmptyScreen();
            return;
        }
        getView().showMovieList(recyclerItemViews);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return data != null;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return getMovieObservable(1, mMovieListType);
    }

    @Override
    protected Observable<?> transformObservable(Observable<?> observable) {
        return PaginationTool.paging(getView().getRecyclerView(), (offset -> observable), LIMIT);
    }

    @Override
    protected <D> Observable<?> transformResponseData(D data) {
        return getViewItemObservable((MovieListResponseDto) data);
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }

    @Override
    public void getMovies(MovieListType type, RecyclerView recyclerView) {
        mMovieListType = type;
        startRequest("DefaultTag");
    }

    private Observable<MovieListResponseDto> getMovieObservable(int offset, MovieListType type) {
        Observable<MovieListResponseDto> observable;
        switch (type) {
            case LATEST:
                observable = mMovieService.getLatestMovie(offset);
                break;
            case NOW_PLAYING:
                observable = mMovieService.getNowPlayingMovie(offset);
                break;
            case POPULAR:
                observable = mMovieService.getPopularMovie(offset);
                break;
            case UPCOMING:
                observable = mMovieService.getUpComingMovie(offset);
                break;
            case TOP_RATED:
                observable = mMovieService.getTopRatedMovie(offset);
                break;
            default:
                throw new IllegalArgumentException("Invalid com.km2labs.mediacontent.movie list type");
        }
        return observable;
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(MovieListResponseDto moviesListResponseData) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();

        if (moviesListResponseData != null) {
            List<Movie> movies = moviesListResponseData.getResults();
            Observable.fromIterable(movies).forEach(movie -> itemViews.add(new MovieViewItem(movie)));
        }

        return Observable.just(itemViews);
    }
}
