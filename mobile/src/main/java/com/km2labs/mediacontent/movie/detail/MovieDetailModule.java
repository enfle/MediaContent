package com.km2labs.mediacontent.movie.detail;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.dagger.scope.InMemoryCache;
import com.km2labs.mediacontent.dagger.scope.PerActivity;
import com.km2labs.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

@Module
public class MovieDetailModule {

    @Provides
    @PerActivity
    public MovieDetailContract.Presenter providePresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new MovieDetailPresenter(movieService, dataCache);
    }
}
