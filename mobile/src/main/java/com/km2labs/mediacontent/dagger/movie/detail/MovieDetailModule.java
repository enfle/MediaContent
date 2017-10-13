package com.km2labs.mediacontent.dagger.movie.detail;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailContract;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailPresenter;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;
import com.km2labs.mediacontent.dagger.core.scope.InMemoryScopeCache;
import com.km2labs.mediacontent.dagger.movie.MovieModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

@Module
public class MovieDetailModule extends MovieModule<MovieDetailActivity> {

    @Provides
    @ActivityScope
    public MovieDetailContract.Presenter providePresenter(MovieService movieService, @InMemoryScopeCache DataCache dataCache) {
        return new MovieDetailPresenter(movieService, dataCache);
    }
}
