package com.km2labs.mediacontent.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.base.adapter.ItemizedRecyclerAdapter;
import com.km2labs.mediacontent.base.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;
import com.km2labs.mediacontent.movie.detail.MovieDetailActivity;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieListFragment extends RecyclerViewFragment<MovieListFragmentContract.View, MovieListPresenter> implements MovieListFragmentContract.View {

    public static final String ARG_MOVIE_LIST_TYPE = "Args:Fragment:Movie:List:MovieType";

    @Override
    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

    @Override
    protected RecyclerViewFragment.LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void onLoadData() {
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
        int movieId = itemView.getMovie().getId();
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE_ID, movieId);
        startActivity(intent);
    }


    @Override
    public void onError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDetached();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    protected boolean enablePullToRefresh() {
        return true;
    }
}
