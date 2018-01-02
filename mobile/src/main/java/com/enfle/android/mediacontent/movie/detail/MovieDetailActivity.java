package com.enfle.android.mediacontent.movie.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.activity.DaggerActivity;
import com.enfle.android.mediacontent.beans.Movie;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
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

    @BindView(R.id.title)
    TextView mTitleTextView;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Inject
    MovieDetailContract.Presenter mPresenter;

    private Movie mMovie;
    private MovieDetailDto mMovieDetailDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);

        mMovie = Parcels.unwrap(getIntent().getParcelableExtra(ARG_MOVIE));
        mTablayout.setupWithViewPager(mViewPager, true);

        mTitleTextView.setText(mMovie.getTitle());
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
        mPresenter.getMovieDetail(mMovie.getId());
    }

    @Override
    public void onMovieDetailReceived(MovieDetailDto movieDetailDto) {
        Log.d("shubham", "Received");
        mMovieDetailDto = movieDetailDto;
        MovieDetailFragmentPagerAdapter adapter = new MovieDetailFragmentPagerAdapter(this, getSupportFragmentManager());
        adapter.setMovieDetailDto(mMovieDetailDto);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onError() {
    }

    @Override
    public void onAuthenticationError(String tag) {
    }
}
