package com.enfle.android.mediacontent.dagger;

import com.enfle.android.mediacontent.dagger.scope.PerActivity;
import com.enfle.android.mediacontent.movie.MovieModule;
import com.enfle.android.mediacontent.movie.detail.MovieDetailActivity;
import com.enfle.android.mediacontent.movie.detail.MovieDetailActivityModule;
import com.enfle.android.mediacontent.movie.detail.MovieDetailModule;
import com.enfle.android.mediacontent.movie.list.MovieListActivity;

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