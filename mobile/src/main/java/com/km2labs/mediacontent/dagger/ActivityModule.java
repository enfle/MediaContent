package com.km2labs.mediacontent.dagger;

import com.km2labs.mediacontent.dagger.scope.ActivityScope;
import com.km2labs.mediacontent.movie.MovieModule;
import com.km2labs.mediacontent.movie.list.MovieListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {


    @ContributesAndroidInjector(modules = {MovieModule.class})
    @ActivityScope
    protected abstract MovieListActivity bindMainActivity();
}