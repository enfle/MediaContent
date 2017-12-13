package com.km2labs.mediacontent.movie.list;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.dagger.scope.ActivityScope;
import com.km2labs.mediacontent.dagger.scope.InMemoryCache;
import com.km2labs.mediacontent.movie.MovieModule;
import com.km2labs.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

@Module
public class MovieListModule  {

    @Provides
    @ActivityScope
    public MovieListFragmentContract.Presenter provideMovieListPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new MovieListPresenter(movieService, dataCache);
    }
}