package com.km2labs.mediacontent.dagger.movie.list;

import com.km2labs.mediacontent.dagger.core.scope.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by subhamtyagi on 13/10/17.
 */

@Module
public abstract class MovieListFragmentProvider {

    @ContributesAndroidInjector(modules = {MovieListModule.class})
    @FragmentScope
    abstract MovieListFragment movieListFragment();

}
