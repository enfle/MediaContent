package com.km2labs.mediacontent.movie.detail.similar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.base.fragments.RecyclerViewFragment;
import com.km2labs.mediacontent.beans.Movie;
import com.km2labs.mediacontent.movie.detail.MovieDetailActivity;
import com.km2labs.mediacontent.movie.list.MovieGridViewItem;

import org.parceler.Parcels;

import java.util.List;

public class SimilarMovieFragment extends RecyclerViewFragment<SimilarMovieContract.View, SimilarMovieContract.Presenter> implements SimilarMovieContract.View {
    public static final String ARG_MOVIE_ID = "Arg:Fragment:Movie:Reviews";

    private Integer mMovieId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            //showEmptyScreen();
            return;
        }
        mMovieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Override
    public void onError() {

    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
        MovieGridViewItem itemView = (MovieGridViewItem) mAdapter.getItems().get(position);
        Movie movieId = itemView.getMovie();
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE_ID, Parcels.wrap(movieId));
        startActivity(intent);
    }


    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.HORIZONTAL_LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void onLoadData() {
        mPresenter.getSimilarMovies(mMovieId);
    }

    @Override
    public void showMovieList(List<RecyclerItemView> recyclerItemViews) {
        super.addItems(recyclerItemViews);
    }

    @Override
    protected int getContentLayoutResId() {
        return 0;
    }
}