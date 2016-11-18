package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.bean.MovieListResponseDto;
import com.km2labs.mediacontent.common.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.common.ui.mvp.NetworkPresenter;
import com.km2labs.mediacontent.common.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class RecommendedFragmentPresenter extends NetworkPresenter<RecommendedFragmentContract.View>
        implements RecommendedFragmentContract.Presenter {

    private MovieService mMovieService;

    public RecommendedFragmentPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    protected void handleError(Throwable throwable) {

    }

    @Override
    public void getRecommendedMovies(Integer movieId) {
        mMovieService.getRecommendedMovies(movieId)
                .compose(RxUtils.applyMainIOSchedulers())
                .flatMap(this::getViewItemObservable)
                .subscribe(this::onLoadCompleted, this::onError);
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(MovieListResponseDto reviewDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        if (reviewDto != null) {
            List<Movie> movies = reviewDto.getResults();
            Observable.from(movies).forEach(review -> itemViews.add(new MovieGridViewItem(review)));
        }
        return Observable.just(itemViews);
    }

    private void onLoadCompleted(List<RecyclerItemView> recyclerItemViews) {
        getView().onLoadingComplete(true);
        getView().showMovieList(recyclerItemViews);
    }


}
