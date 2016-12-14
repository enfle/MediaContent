package com.km2labs.mediacontent.loaders.movie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.km2labs.framework.network.RetrofitHelper;
import com.km2labs.framework.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.review.ReviewFragmentContract;
import com.km2labs.mediacontent.common.movie.review.ReviewFragmentPresenter;
import com.km2labs.mediacontent.core.adapter.ItemizedRecyclerAdapter;
import com.km2labs.mediacontent.core.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.loaders.RecyclerViewFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class ReviewFragment extends RecyclerViewFragment<ReviewFragmentContract.View, ReviewFragmentContract.Presenter>
        implements ReviewFragmentContract.View {

    public static final String ARG_MOVIE_ID = "Arg:Fragment:Movie:Id:Reviews";

    private Integer mMovieId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            showEmptyScreen();
            return;
        }
        mMovieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Override
    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.VERTICAL_LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected int getLoaderId() {
        return 13;
    }

    @Override
    protected void onLoadData() {
        mPresenter.loadMovieReview(mMovieId);
    }

    @Override
    public void onError() {

    }

    @Override
    protected PresenterFactory<ReviewFragmentContract.Presenter> getPresenterFactory() {
        return () -> new ReviewFragmentPresenter(RetrofitHelper.getRetrofitHelper()
                .createService(MovieService.class), new InMemoryCache());
    }

    @Override
    public void showMovieReview(List<RecyclerItemView> reviewList) {
        super.addItems(reviewList);
    }
}
