package com.km2labs.mediacontent.movie;

import com.km2labs.mediacontent.dagger.scope.FragmentScope;
import com.km2labs.mediacontent.movie.list.MovieListFragment;
import com.km2labs.mediacontent.movie.list.MovieListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

@Module
public abstract class MovieModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {MovieListModule.class})
    abstract public MovieListFragment movieListFragment();
}