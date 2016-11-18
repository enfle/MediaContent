package com.km2labs.mediacontent.loaders.movie.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.Movie;
import com.km2labs.mediacontent.loaders.PresenterActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;

public class MovieDetailActivity extends PresenterActivity {

    public static final String ARG_MOVIE = "Args:Activity:Movie:Detail:Id";

    @BindView(R.id.backdrop)
    ImageView mBackdrop;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    MovieDetailFragmentPagerAdapter mAdapter;

    private Movie mMovie;

    @Override
    protected int getContentLayoutId() {
        return R.layout.movie_detail_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getIntent().getParcelableExtra(ARG_MOVIE));
        updateUi();
    }

    public void updateUi() {
        String imagePath = mMovie.getBackdropPath();
        Picasso.with(mBackdrop.getContext())
                .load("http://image.tmdb.org/t/p/w500" + imagePath)
                .fit()
                .into(mBackdrop);

        mAdapter = new MovieDetailFragmentPagerAdapter(this, getSupportFragmentManager());
        mAdapter.setMovieId(mMovie.getId());
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    private class MovieDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private Context mContext;

        private Integer mMovieId;

        MovieDetailFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        void setMovieId(Integer movieDetailDto) {
            mMovieId = movieDetailDto;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putInt(MovieOverviewFragment.ARG_MOVIE_ID, mMovieId);
                    fragment = new MovieOverviewFragment();
                    break;
                case 1:
                    bundle.putInt(VideoFragment.ARG_MOVIE_ID, mMovieId);
                    fragment = new VideoFragment();
                    break;
                case 2:
                    bundle.putInt(ReviewFragment.ARG_MOVIE_ID, mMovieId);
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
            if (mMovieId == null) {
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
