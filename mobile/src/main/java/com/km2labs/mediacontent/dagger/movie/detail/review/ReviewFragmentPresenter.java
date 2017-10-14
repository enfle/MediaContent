package com.km2labs.mediacontent.dagger.movie.detail.review;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Review;
import com.km2labs.mediacontent.common.movie.bean.Reviews;
import com.km2labs.mediacontent.common.movie.detail.ReviewListItem;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter;
import com.km2labs.mediacontent.core.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class ReviewFragmentPresenter extends AbsNetworkPresenter<ReviewFragmentContract.View>
        implements ReviewFragmentContract.Presenter {

    private MovieService mMovieService;

    public ReviewFragmentPresenter(MovieService movieService, DataCache dataCache) {
        mMovieService = movieService;
    }

    @Override
    public void loadMovieReview(Integer movieId) {
        mMovieService.getMovieReviews(movieId)
                .compose(RxUtils.applyMainIOSchedulers())
                .flatMap(this::getViewItemObservable)
                .subscribe(this::onLoadCompleted, error -> {
                });
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
        getView().showMovieReview(recyclerItemViews);
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
    public void retry(String tag) {

    }
}
