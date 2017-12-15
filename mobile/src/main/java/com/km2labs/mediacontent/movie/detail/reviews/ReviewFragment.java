package com.km2labs.mediacontent.movie.detail.reviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class ReviewFragment extends RecyclerViewFragment<ReviewFragmentContract.View, ReviewFragmentContract.Presenter> implements ReviewFragmentContract.View {

    public static final String ARG_MOVIE_ID = "Arg:Fragment:Movie:ID";

    private Integer mMovieId;

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewAttached(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onViewDetached();
    }

    @Override
    protected void onLoadData() {
        mMovieId = getArguments().getInt(ARG_MOVIE_ID);
        mPresenter.loadMovieReview(mMovieId);
    }

    @Override
    public void onError() {

    }

    @Override
    public void showMovieReview(List<RecyclerItemView> reviewList) {
        addItems(reviewList);
    }
}
