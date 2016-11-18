package com.km2labs.mediacontent.dagger.movie.detail;

import com.km2labs.mediacontent.common.movie.detail.ReviewFragmentContract;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.dagger.core.ui.fragment.RecyclerViewFragment;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivitySubcomponentBuilders;

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
    protected void injectMembers(ActivitySubcomponentBuilders activitySubcomponentBuilders) {

    }

    @Override
    public void showMovieReview(List<RecyclerItemView> reviewList) {

    }

    @Override
    public void onError() {

    }
}
