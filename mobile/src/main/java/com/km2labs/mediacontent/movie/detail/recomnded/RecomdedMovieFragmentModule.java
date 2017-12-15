package com.km2labs.mediacontent.movie.detail.recomnded;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.dagger.scope.InMemoryCache;
import com.km2labs.mediacontent.dagger.scope.PerChildFragment;
import com.km2labs.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 13/12/17.
 */

@Module
public class RecomdedMovieFragmentModule {

    @Provides
    @PerChildFragment
    public RecommendedFragmentContract.Presenter providesPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new RecommendedFragmentPresenter(movieService, dataCache);
    }
}
