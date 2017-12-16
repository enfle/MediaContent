package com.enfle.android.mediacontent.movie.detail.reviews;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Review;
import com.enfle.android.mediacontent.beans.Reviews;
import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.mvp.BaseNetworkPresenter;
import com.enfle.android.mediacontent.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class ReviewFragmentPresenter extends BaseNetworkPresenter<ReviewFragmentContract.View> implements ReviewFragmentContract.Presenter {

    private MovieService mMovieService;

    private int mMovieId;

    public ReviewFragmentPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    public void loadMovieReview(Integer movieId) {
        mMovieId = movieId;
        startRequest();
    }

    private Observable<List<RecyclerItemView>> getViewItemObservable(Reviews reviewDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        if (reviewDto != null) {
            List<Review> reviews = reviewDto.getResults();
            Observable.fromIterable(reviews).forEach(review -> itemViews.add(new ReviewListItem(review)));
        }
        return Observable.just(itemViews);
    }


    @Override
    protected <D> Observable<?> transformResponseData(D data) {
        return getViewItemObservable((Reviews) data);
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        getView().onLoadingComplete(true);
        getView().showMovieReview((List<RecyclerItemView>) data);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return false;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return mMovieService.getMovieReviews(mMovieId);
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }
}
