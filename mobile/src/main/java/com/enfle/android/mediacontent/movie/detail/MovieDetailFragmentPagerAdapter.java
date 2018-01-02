package com.enfle.android.mediacontent.movie.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.cast.CastFragment;
import com.enfle.android.mediacontent.movie.detail.overview.MovieOverviewFragment;
import com.enfle.android.mediacontent.movie.detail.reviews.ReviewFragment;
import com.enfle.android.mediacontent.movie.detail.video.VideoFragment;

import org.parceler.Parcels;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;

    private MovieDetailDto mMovieDetailDto;

    MovieDetailFragmentPagerAdapter(Context context, FragmentManager fm) {
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
                bundle.putInt(MovieOverviewFragment.ARG_MOVIE_ID, mMovieDetailDto.getId());
                fragment = new MovieOverviewFragment();
                break;
            case 1:
                bundle.putParcelable(VideoFragment.ARG_MOVIE_VIDEOS, Parcels.wrap(mMovieDetailDto.getVideos()));
                bundle.putParcelable(VideoFragment.ARG_MOVIE_IMAGES, Parcels.wrap(mMovieDetailDto.getImages()));
                fragment = new VideoFragment();
                break;
            case 2:
                bundle.putInt(ReviewFragment.ARG_MOVIE_ID, mMovieDetailDto.getId());
                fragment = new ReviewFragment();
                break;
            case 3:
                bundle.putParcelable(CastFragment.ARG_MOVIE_CAST, Parcels.wrap(mMovieDetailDto.getCredits().getCast()));
                fragment = new CastFragment();
                break;
            case 4:
                bundle.putInt(ReviewFragment.ARG_MOVIE_ID, mMovieDetailDto.getId());
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
        return 5;
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
            case 3:
                return mContext.getString(R.string.cast);
            case 4:
                return mContext.getString(R.string.crew);
            default:
                throw new IllegalArgumentException("Invalid position");
        }
    }
}
