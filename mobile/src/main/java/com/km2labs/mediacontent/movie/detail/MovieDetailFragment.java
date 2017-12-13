package com.km2labs.mediacontent.movie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.base.fragments.BaseLoadingFragment;
import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A placeholder fragment containing a simple view.
 */


public class MovieDetailFragment extends BaseLoadingFragment<MovieDetailContract.View, MovieDetailPresenter> implements MovieDetailContract.View {

    public static final String ARG_MOVIE_ID = "Args:Fragment:Movie:Detail:Id";

    @BindView(R.id.backdrop)
    ImageView mBackdrop;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Inject
    MovieDetailPresenter mPresenter;

    private MovieDetailDto mMovieDetailDto;

    //@Override
    protected int getLayoutResId() {
        return R.layout.movie_detail_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        int movieId = bundle.getInt(ARG_MOVIE_ID);

        if (movieId < 1) {
            return;
        }
        mPresenter.getMovieDetail(movieId);
    }

    @Override
    public void onMovieDetailReceived(MovieDetailDto movieDetailDto) {
        MovieDetailFragmentPagerAdapter adapter = new MovieDetailFragmentPagerAdapter(getContext(), getFragmentManager());
        adapter.setMovieDetailDto(movieDetailDto);
        mViewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewPager, true);

        String imagePath = movieDetailDto.getBackdropPath();
        Picasso.with(mBackdrop.getContext())
                .load("http://image.tmdb.org/t/p/w500/" + imagePath)
                .fit()
                .into(mBackdrop);
    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onNetworkError(String tag) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onAuthenticationError(String tag) {

    }
}
