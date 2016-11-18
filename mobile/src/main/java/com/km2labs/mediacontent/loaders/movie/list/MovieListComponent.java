package com.km2labs.mediacontent.loaders.movie.list;

import com.km2labs.mediacontent.dagger.core.scope.PersistentScope;
import com.km2labs.mediacontent.dagger.movie.list.MovieListModule;

import dagger.Subcomponent;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

@Subcomponent(modules = {MovieListModule.class})
@PersistentScope
public interface MovieListComponent {

    void inject(MovieListFragment movieListFragment);
}
