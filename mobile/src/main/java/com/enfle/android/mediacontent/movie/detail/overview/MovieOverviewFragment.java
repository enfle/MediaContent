package com.enfle.android.mediacontent.movie.detail.overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.fragments.DaggerFragment;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.movie.detail.recomnded.RecommendedMovieFragment;
import com.enfle.android.mediacontent.movie.detail.similar.SimilarMovieFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieOverviewFragment extends DaggerFragment<OverviewFragmentContract.View, OverviewFragmentContract.Presenter>
        implements OverviewFragmentContract.View, HasSupportFragmentInjector {

    public static final String ARG_MOVIE_ID = "Args:Fragment:Movie:Detail";

    @BindView(R.id.overview_text_view)
    TextView mOverviewText;

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    private Integer mMovieId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            showEmptyScreen();
            return;
        }
        mMovieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewAttached(this);
        SimilarMovieFragment similarMovieFragment = new SimilarMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SimilarMovieFragment.ARG_MOVIE_ID, mMovieId);
        similarMovieFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().add(R.id.similar_movie_container, similarMovieFragment).commit();

        RecommendedMovieFragment recommendedMovieFragment = new RecommendedMovieFragment();
        Bundle recBundle = new Bundle();
        recBundle.putInt(RecommendedMovieFragment.ARG_MOVIE_ID, mMovieId);
        recommendedMovieFragment.setArguments(recBundle);
        getChildFragmentManager().beginTransaction().add(R.id.recommended_movie_container, recommendedMovieFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onViewDetached();
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.movie_detail_overview_fragment;
    }

    @Override
    protected void loadData() {
        mPresenter.getMovieDetail(mMovieId);
    }

    @Override
    public void onError() {
    }

    @Override
    public void onMovieDetailLoaded(MovieDetailDto movieDetailDto) {
        mOverviewText.setText(movieDetailDto.getOverview());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
