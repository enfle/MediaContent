package com.km2labs.mediacontent.dagger.movie.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.dagger.movie.detail.overview.MovieOverviewFragment;
import com.km2labs.mediacontent.dagger.movie.detail.review.ReviewFragment;
import com.km2labs.mediacontent.dagger.movie.detail.video.VideoFragment;

import org.parceler.Parcels;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {

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
                fragment = new VideoFragment();
                break;
            case 2:
                bundle.putParcelable(ReviewFragment.ARG_REVIEWS, Parcels.wrap(mMovieDetailDto.getVideos()));
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
