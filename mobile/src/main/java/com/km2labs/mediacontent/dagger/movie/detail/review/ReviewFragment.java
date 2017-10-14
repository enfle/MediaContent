package com.km2labs.mediacontent.dagger.movie.detail.review;


import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.dagger.core.ui.fragment.RecyclerViewFragment;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class ReviewFragment extends RecyclerViewFragment implements ReviewFragmentContract.View {

    public static final String ARG_REVIEWS = "Arg:Fragment:Movie:Reviews";

    @Override
    protected int getLayoutView() {
        return 0;
    }

    @Override
    public void showMovieReview(List<RecyclerItemView> reviewList) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return null;
    }
}
