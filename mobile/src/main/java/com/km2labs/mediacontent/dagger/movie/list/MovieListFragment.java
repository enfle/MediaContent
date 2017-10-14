package com.km2labs.mediacontent.dagger.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.MovieListType;
import com.km2labs.mediacontent.common.movie.list.MovieListFragmentContract;
import com.km2labs.mediacontent.common.movie.list.MovieListPresenter;
import com.km2labs.mediacontent.common.movie.list.MovieViewItem;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.dagger.core.ui.fragment.RecyclerViewFragment;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class MovieListFragment extends RecyclerViewFragment implements MovieListFragmentContract.View {
    public static final String ARG_MOVIE_LIST_TYPE = "Args:Fragment:Movie:List:MovieType";

    @Inject
    MovieListPresenter mPresenter;

    @Override
    protected int getLayoutView() {
        return R.layout.movie_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    protected RecyclerViewFragment.LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            onEmptyResult();
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
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        MovieViewItem itemView = (MovieViewItem) mAdapter.getItems().get(position);
        int movieId = itemView.getMovie().getId();
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE_ID, movieId);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDetached();
    }

    @Override
    public void onError(Throwable error) {
    }

    @Override
    protected void onRetry() {
    }
}
