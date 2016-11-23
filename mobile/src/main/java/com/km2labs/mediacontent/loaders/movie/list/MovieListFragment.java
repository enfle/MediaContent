package com.km2labs.mediacontent.loaders.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.common.movie.MovieListType;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.list.MovieListFragmentContract;
import com.km2labs.mediacontent.common.movie.list.MovieViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.loaders.DefaultPresenterFactory;
import com.km2labs.mediacontent.loaders.RecyclerViewFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;
import com.km2labs.mediacontent.loaders.movie.detail.MovieDetailActivity;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieListFragment extends RecyclerViewFragment<MovieListFragmentContract.View, MovieListFragmentContract.Presenter>
        implements MovieListFragmentContract.View {

    public static final String ARG_MOVIE_LIST_TYPE = "Args:Fragment:Movie:List:MovieType";

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.VERTICAL_LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void onLoadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            showEmptyScreen();
            return;
        }
        MovieListType type = MovieListType.valueOf(bundle.getString(ARG_MOVIE_LIST_TYPE));
        mPresenter.getMovies(type, mRecyclerView);
    }

    @Override
    public void showMovieList(List<RecyclerItemView> recyclerItemViews) {
        super.addItems(recyclerItemViews);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        MovieViewItem itemView = (MovieViewItem) mAdapter.getItems().get(position);
        Movie movieId = itemView.getMovie();
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, Parcels.wrap(movieId));
        startActivity(intent);
    }

    @Override
    protected int getLoaderId() {
        MovieListType type = MovieListType.valueOf(getArguments().getString(ARG_MOVIE_LIST_TYPE));
        return type.ordinal();
    }

    @Override
    protected boolean enablePullToRefresh() {
        return true;
    }

    @Override
    protected PresenterFactory<MovieListFragmentContract.Presenter> getPresenterFactory() {
        return new DefaultPresenterFactory<>(DefaultPresenterFactory.TYPE_MOVIE_LIST);
    }

    @Override
    public void onError() {

    }
}
