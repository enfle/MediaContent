package com.enfle.android.mediacontent.movie.detail.reviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.enfle.android.mediacontent.base.fragments.DaggerRecyclerViewFragment;
import com.enfle.android.mediacontent.base.fragments.LayoutManagerType;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class ReviewFragment extends DaggerRecyclerViewFragment<ReviewFragmentContract.View, ReviewFragmentContract.Presenter> implements ReviewFragmentContract.View {

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
    protected void loadData() {
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
