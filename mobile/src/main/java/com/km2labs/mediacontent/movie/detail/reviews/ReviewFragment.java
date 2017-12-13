package com.km2labs.mediacontent.movie.detail.reviews;

import com.km2labs.mediacontent.base.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class ReviewFragment extends RecyclerViewFragment<ReviewFragmentContract.View, ReviewFragmentContract.Presenter> implements ReviewFragmentContract.View {

    public static final String ARG_REVIEWS = "Arg:Fragment:Movie:Reviews";

    @Override
    protected RecyclerAdapter getAdapter() {
        return null;
    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return null;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showMovieReview(List<RecyclerItemView> reviewList) {

    }

    @Override
    protected int getContentLayoutResId() {
        return 0;
    }
}
