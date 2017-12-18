package com.enfle.android.mediacontent.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enfle.android.mediacontent.base.fragments.DaggerRecyclerViewFragment;
import com.enfle.android.mediacontent.base.fragments.LayoutManagerType;
import com.enfle.android.mediacontent.base.adapter.ItemizedRecyclerAdapter;
import com.enfle.android.mediacontent.base.adapter.RecyclerAdapter;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.beans.Movie;
import com.enfle.android.mediacontent.movie.detail.MovieDetailActivity;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieListFragment extends DaggerRecyclerViewFragment<MovieListFragmentContract.View, MovieListPresenter> implements MovieListFragmentContract.View {

    public static final String ARG_MOVIE_LIST_TYPE = "Args:Fragment:Movie:List:MovieType";

    @Override
    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            showEmptyScreen();
            return;
        }
        MovieListType type = MovieListType.valueOf(bundle.getString(ARG_MOVIE_LIST_TYPE));
        mPresenter.onViewAttached(this);
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
        Movie movie = itemView.getMovie();
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, Parcels.wrap(movie));
        startActivity(intent);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onViewDetached();
    }

    @Override
    protected boolean enablePullToRefresh() {
        return true;
    }
}
