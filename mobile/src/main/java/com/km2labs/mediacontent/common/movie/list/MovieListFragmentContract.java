package com.km2labs.mediacontent.common.movie.list;

import android.support.v7.widget.RecyclerView;

import com.km2labs.framework.mvp.ILoadingView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.mediacontent.common.movie.MovieListType;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface MovieListFragmentContract {


    interface Presenter extends INetworkPresenter<View> {

        void getMovies(MovieListType type, RecyclerView recyclerView);
    }

    interface View extends ILoadingView {

        void showMovieList(List<RecyclerItemView> recyclerItemViews);

        RecyclerView getRecyclerView();

    }
}
