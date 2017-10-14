package com.km2labs.mediacontent.common.movie.list;

import android.support.v7.widget.RecyclerView;

import com.km2labs.mediacontent.common.movie.MovieListType;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.core.mvp.view.ILoadingView;
import com.km2labs.mediacontent.core.mvp.presenter.NetworkPresenter;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface MovieListFragmentContract {


    interface Presenter extends NetworkPresenter<View> {

        void getMovies(MovieListType type, RecyclerView recyclerView);
    }

    interface View extends ILoadingView {

        void showMovieList(List<RecyclerItemView> recyclerItemViews);
    }
}
