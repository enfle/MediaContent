package com.km2labs.mediacontent.dagger.movie.list;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.list.MovieListFragmentContract;
import com.km2labs.mediacontent.common.movie.list.MovieListPresenter;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;
import com.km2labs.mediacontent.dagger.core.scope.InMemoryScopeCache;
import com.km2labs.mediacontent.dagger.movie.MovieModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

@Module
public class MovieListModule extends MovieModule<MovieListActivity> {

    @Provides
    @ActivityScope
    public MovieListFragmentContract.Presenter provideMovieListPresenter(MovieService movieService, @InMemoryScopeCache DataCache dataCache) {
        return new MovieListPresenter(movieService, dataCache);
    }
}
