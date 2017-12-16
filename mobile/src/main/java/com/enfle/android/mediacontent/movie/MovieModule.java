package com.enfle.android.mediacontent.movie;

import com.enfle.android.mediacontent.dagger.scope.PerFragment;
import com.enfle.android.mediacontent.movie.list.MovieListFragment;
import com.enfle.android.mediacontent.movie.list.MovieListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

@Module
public abstract class MovieModule {

    @PerFragment
    @ContributesAndroidInjector(modules = {MovieListModule.class})
    abstract public MovieListFragment movieListFragment();
}
