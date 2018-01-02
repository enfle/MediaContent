package com.enfle.android.mediacontent.movie.detail.overview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.base.fragments.LayoutManagerType;
import com.enfle.android.mediacontent.base.fragments.RecyclerViewFragment;
import com.enfle.android.mediacontent.beans.Movie;
import com.enfle.android.mediacontent.movie.detail.MovieDetailActivity;
import com.enfle.android.mediacontent.movie.detail.MovieGridViewItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : Subham Tyagi
 * Created on :  05/10/16.
 */

public class MovieListFragment extends RecyclerViewFragment {

    public static final String ARG_MOVIES = "Arg:Fragment:Movie:Movies";

    private List<Movie> movieList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            //showEmptyScreen();
            return;
        }
        movieList = Parcels.unwrap(bundle.getParcelable(ARG_MOVIES));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<RecyclerItemView> recyclerItemViews = new ArrayList<>();
        for (Movie movie : movieList) {
            recyclerItemViews.add(new MovieGridViewItem(movie));
        }
        addItems(recyclerItemViews);
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
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.HORIZONTAL_LINEAR_LAYOUT_MANAGER;
    }
}
