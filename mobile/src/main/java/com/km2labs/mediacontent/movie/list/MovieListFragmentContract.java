package com.km2labs.mediacontent.movie.list;

import android.support.v7.widget.RecyclerView;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

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
