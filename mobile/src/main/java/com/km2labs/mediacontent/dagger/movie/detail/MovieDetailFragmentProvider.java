package com.km2labs.mediacontent.dagger.movie.detail;

import com.km2labs.mediacontent.dagger.core.scope.FragmentScope;
import com.km2labs.mediacontent.dagger.movie.detail.review.ReviewFragment;
import com.km2labs.mediacontent.dagger.movie.detail.review.ReviewModule;
import com.km2labs.mediacontent.dagger.movie.detail.video.VideoFragment;
import com.km2labs.mediacontent.dagger.movie.detail.video.VideoModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by subhamtyagi on 13/10/17.
 */

@Module
public abstract class MovieDetailFragmentProvider {

    @ContributesAndroidInjector(modules = {ReviewModule.class})
    @FragmentScope
    abstract ReviewFragment reviewFragment();

    @ContributesAndroidInjector(modules = {VideoModule.class})
    @FragmentScope
    abstract VideoFragment videoFragment();
}
