package com.km2labs.mediacontent.loaders.movie.detail;

import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailContract;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailPresenter;
import com.km2labs.mediacontent.dagger.core.scope.PersistentScope;
import com.km2labs.mediacontent.loaders.movie.MovieModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

@Module
public class MovieDetailModule extends MovieModule {


    @Provides
    @PersistentScope
    public MovieDetailContract.Presenter providePresenter(MovieService movieService) {
        return new MovieDetailPresenter(movieService, new InMemoryCache());
    }
}
