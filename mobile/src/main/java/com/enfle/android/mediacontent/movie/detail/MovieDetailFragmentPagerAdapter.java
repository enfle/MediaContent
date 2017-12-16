package com.enfle.android.mediacontent.movie.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.movie.detail.overview.MovieOverviewFragment;
import com.enfle.android.mediacontent.movie.detail.reviews.ReviewFragment;
import com.enfle.android.mediacontent.movie.detail.video.VideoFragment;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    private MovieDetailDto mMovieDetailDto;
    private Integer movieId;

    public MovieDetailFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public void setMovieDetailDto(MovieDetailDto movieDetailDto) {
        mMovieDetailDto = movieDetailDto;
    }


    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putInt(MovieOverviewFragment.ARG_MOVIE_ID, movieId);
                fragment = new MovieOverviewFragment();
                break;
            case 1:
                bundle.putInt(VideoFragment.ARG_MOVIE_ID, movieId);
                fragment = new VideoFragment();
                break;
            case 2:
                bundle.putInt(ReviewFragment.ARG_MOVIE_ID, movieId);
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
//        if (mMovieDetailDto == null) {
//            return 0;
//        }
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
