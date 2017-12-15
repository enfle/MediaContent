package com.km2labs.mediacontent.movie.detail;

import com.km2labs.mediacontent.dagger.scope.PerFragment;
import com.km2labs.mediacontent.movie.detail.overview.MovieOverviewFragment;
import com.km2labs.mediacontent.movie.detail.overview.OverviewFragmentModule;
import com.km2labs.mediacontent.movie.detail.reviews.ReviewFragment;
import com.km2labs.mediacontent.movie.detail.reviews.ReviewFragmentModule;
import com.km2labs.mediacontent.movie.detail.video.VideoFragment;
import com.km2labs.mediacontent.movie.detail.video.VideoFragmentModule;

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
