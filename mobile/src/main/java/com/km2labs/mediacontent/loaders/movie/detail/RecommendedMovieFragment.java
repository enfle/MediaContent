package com.km2labs.mediacontent.loaders.movie.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.common.RetrofitHelper;
import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.common.movie.detail.RecommendedFragmentContract;
import com.km2labs.mediacontent.common.movie.detail.RecommendedFragmentPresenter;
import com.km2labs.mediacontent.common.movie.list.MovieGridViewItem;
import com.km2labs.mediacontent.common.ui.adapter.RecyclerItemView;
import com.km2labs.mediacontent.loaders.RecyclerViewFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public class RecommendedMovieFragment extends RecyclerViewFragment<RecommendedFragmentContract.View, RecommendedFragmentContract.Presenter> implements RecommendedFragmentContract.View {

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
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, Parcels.wrap(movieId));
        startActivity(intent);
    }

    @Override
    protected PresenterFactory<RecommendedFragmentContract.Presenter> getPresenterFactory() {
        return () -> new RecommendedFragmentPresenter(RetrofitHelper.getRetrofitHelper().createService(MovieService.class), new InMemoryCache());
    }


    @Override
    protected int getLoaderId() {
        return 15;
    }

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.HORIZONTAL_LINEAR_LAYOUT_MANAGER;
    }

    @Override
    protected void onLoadData() {
        mPresenter.getRecommendedMovies(mMovieId);
    }

    @Override
    public void showMovieList(List<RecyclerItemView> recyclerItemViews) {
        super.addItems(recyclerItemViews);
    }
}
