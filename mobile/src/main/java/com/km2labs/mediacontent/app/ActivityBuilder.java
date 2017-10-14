package com.km2labs.mediacontent.app;

import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailFragmentProvider;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailActivity;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailModule;
import com.km2labs.mediacontent.dagger.movie.list.MovieListActivity;
import com.km2labs.mediacontent.dagger.movie.list.MovieListFragmentProvider;
import com.km2labs.mediacontent.dagger.movie.list.MovieListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  14/11/16.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MovieListFragmentProvider.class})
    @ActivityScope
    abstract MovieListActivity movieListActivityComponentBuilder();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MovieDetailModule.class, MovieDetailFragmentProvider.class})
    public abstract MovieDetailActivity movieDetailActivityComponentBuilder();
}
