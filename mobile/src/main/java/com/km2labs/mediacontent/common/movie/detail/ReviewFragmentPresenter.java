package com.km2labs.mediacontent.common.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Review;
import com.km2labs.mediacontent.common.movie.bean.Reviews;
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

public class ReviewFragmentPresenter extends NetworkPresenter<ReviewFragmentContract.View>
        implements ReviewFragmentContract.Presenter {

    private MovieService mMovieService;

    public ReviewFragmentPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    protected void handleError(Throwable throwable) {

    }

    @Override
    public void loadMovieReview(Integer movieId) {
        mMovieService.getMovieReviews(movieId)
                .compose(RxUtils.applyMainIOSchedulers())
                .flatMap(this::getViewItemObservable)
                .subscribe(this::onLoadCompleted, this::onError);
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(Reviews reviewDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        if (reviewDto != null) {
            List<Review> reviews = reviewDto.getResults();
            Observable.from(reviews).forEach(review -> itemViews.add(new ReviewListItem(review)));
        }
        return Observable.just(itemViews);
    }

    private void onLoadCompleted(List<RecyclerItemView> recyclerItemViews) {
        getView().onLoadingComplete(true);
        getView().showMovieReview(recyclerItemViews);
    }
}
