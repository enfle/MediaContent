package com.enfle.android.mediacontent.movie.list;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.dagger.scope.InMemoryCache;
import com.enfle.android.mediacontent.dagger.scope.PerActivity;
import com.enfle.android.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

@Module
public class MovieListModule {

    @Provides
    @PerActivity
    public MovieListFragmentContract.Presenter provideMovieListPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new MovieListPresenter(movieService, dataCache);
    }
}
