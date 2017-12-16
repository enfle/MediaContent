package com.enfle.android.mediacontent.movie.detail;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.dagger.scope.InMemoryCache;
import com.enfle.android.mediacontent.dagger.scope.PerActivity;
import com.enfle.android.mediacontent.service.MovieService;

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
