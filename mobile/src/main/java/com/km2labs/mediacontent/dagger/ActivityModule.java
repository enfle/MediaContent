package com.km2labs.mediacontent.dagger;

import com.km2labs.mediacontent.dagger.scope.PerActivity;
import com.km2labs.mediacontent.movie.MovieModule;
import com.km2labs.mediacontent.movie.detail.MovieDetailActivity;
import com.km2labs.mediacontent.movie.detail.MovieDetailActivityModule;
import com.km2labs.mediacontent.movie.detail.MovieDetailModule;
import com.km2labs.mediacontent.movie.list.MovieListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {


    @ContributesAndroidInjector(modules = {MovieModule.class})
    @PerActivity
    protected abstract MovieListActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {MovieDetailActivityModule.class, MovieDetailModule.class})
    @PerActivity
    protected abstract MovieDetailActivity bindDetailActivity();
}