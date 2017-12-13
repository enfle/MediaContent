package com.km2labs.mediacontent.movie.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.base.activity.DaggerActivity;
import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.movie.detail.reviews.ReviewFragment;
import com.km2labs.mediacontent.movie.detail.video.VideoFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends DaggerActivity implements MovieDetailContract.View {

    public static final String ARG_MOVIE_ID = "Args:Activity:Movie:Detail:Id";

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
    MovieDetailFragmentPagerAdapter mAdapter;
    private MovieDetailDto mMovieDetailDto;
    private int mMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);

        mMovieId = getIntent().getIntExtra(ARG_MOVIE_ID, 0);
        mAdapter = new MovieDetailFragmentPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager, true);
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

        if (mMovieId < 1) {
            return;
        }
        mPresenter.getMovieDetail(mMovieId);
    }

    @Override
    public void onMovieDetailReceived(MovieDetailDto movieDetailDto) {
        mMovieDetailDto = movieDetailDto;
        String imagePath = movieDetailDto.getBackdropPath();
        Picasso.with(mBackdrop.getContext())
                .load("http://image.tmdb.org/t/p/w500" + imagePath)
                .fit()
                .into(mBackdrop);
        mAdapter.setMovieDetailDto(mMovieDetailDto);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onError() {

    }

    @Override
    public void onAuthenticationError(String tag) {

    }


    private static class MovieDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private Context mContext;

        private MovieDetailDto mMovieDetailDto;

        public MovieDetailFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        public void setMovieDetailDto(MovieDetailDto movieDetailDto) {
            mMovieDetailDto = movieDetailDto;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putParcelable(MovieOverviewFragment.ARG_MOVIE_DETAIL, Parcels.wrap(mMovieDetailDto));
                    fragment = new MovieOverviewFragment();
                    break;
                case 1:
                    bundle.putParcelable(VideoFragment.ARG_VIDEO_LIST, Parcels.wrap(mMovieDetailDto.getVideos()));
                    bundle.putParcelable(VideoFragment.ARG_BACKDROP_LIST, Parcels.wrap(mMovieDetailDto.getImages().getBackdrops()));
                    fragment = new VideoFragment();
                    break;
                case 2:
                    bundle.putParcelable(ReviewFragment.ARG_REVIEWS, Parcels.wrap(mMovieDetailDto.getReviews()));
                    fragment = new ReviewFragment();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid position");
            }
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            if (mMovieDetailDto == null) {
                return 0;
            }
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getString(R.string.overview);
                case 1:
                    return mContext.getString(R.string.video);
                case 2:
                    return mContext.getString(R.string.reviews);
                default:
                    throw new IllegalArgumentException("Invalid position");
            }
        }
    }
}
