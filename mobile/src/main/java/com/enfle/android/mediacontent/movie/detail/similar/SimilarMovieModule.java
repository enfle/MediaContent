package com.enfle.android.mediacontent.movie.detail.similar;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.dagger.scope.InMemoryCache;
import com.enfle.android.mediacontent.dagger.scope.PerChildFragment;
import com.enfle.android.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 13/12/17.
 */

@Module
public class SimilarMovieModule {

    @PerChildFragment
    @Provides
    public SimilarMovieContract.Presenter providesPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new SimilarMoviePresenter(movieService, dataCache);
    }
}
