package com.enfle.android.mediacontent.movie.detail;

import com.enfle.android.mediacontent.dagger.scope.PerFragment;
import com.enfle.android.mediacontent.movie.detail.overview.MovieOverviewFragment;
import com.enfle.android.mediacontent.movie.detail.overview.OverviewFragmentModule;
import com.enfle.android.mediacontent.movie.detail.reviews.ReviewFragment;
import com.enfle.android.mediacontent.movie.detail.reviews.ReviewFragmentModule;
import com.enfle.android.mediacontent.movie.detail.video.VideoFragment;
import com.enfle.android.mediacontent.movie.detail.video.VideoFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by subhamtyagi on 13/12/17.
 */

@Module
public abstract class MovieDetailActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = {OverviewFragmentModule.class})
    abstract public MovieOverviewFragment movieListFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {ReviewFragmentModule.class})
    abstract public ReviewFragment reviewFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {VideoFragmentModule.class})
    abstract public VideoFragment videoFragment();
}
