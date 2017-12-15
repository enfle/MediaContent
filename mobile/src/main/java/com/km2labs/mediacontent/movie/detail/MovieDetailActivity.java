package com.km2labs.mediacontent.movie.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.base.activity.DaggerActivity;
import com.km2labs.mediacontent.beans.Movie;
import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends DaggerActivity implements MovieDetailContract.View {

    public static final String ARG_MOVIE = "Args:Activity:Movie:Detail";

    @BindView(R.id.backdrop)
    ImageView mBackdrop;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Inject
    MovieDetailContract.Presenter mPresenter;

    private MovieDetailFragmentPagerAdapter mAdapter;

    private MovieDetailDto mMovieDetailDto;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);
        mMovie = Parcels.unwrap(getIntent().getParcelableExtra(ARG_MOVIE));
        mAdapter = new MovieDetailFragmentPagerAdapter(this, getSupportFragmentManager());
        mAdapter.setMovieId(mMovie.getId());
        mTablayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(mAdapter);
        String imagePath = mMovie.getBackdropPath();
        Picasso.with(mBackdrop.getContext())
                .load("http://image.tmdb.org/t/p/w500" + imagePath)
                .fit()
                .into(mBackdrop);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
        loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewDetached();
    }


    @Override
    public void onLoadingStart() {

    }

    @Override
    public void onLoadingComplete(boolean success) {

    }

    @Override
    public void showEmptyScreen() {

    }

    @Override
    public void onNetworkError(String tag) {

    }

    protected void loadData() {
        if (mMovie == null) {
            return;
        }
        // mPresenter.getMovieDetail(mMovie.getId());
    }

    @Override
    public void onMovieDetailReceived(MovieDetailDto movieDetailDto) {
//        mMovieDetailDto = movieDetailDto;
//        mAdapter.setMovieDetailDto(mMovieDetailDto);
//        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onError() {
    }

    @Override
    public void onAuthenticationError(String tag) {
    }
}
