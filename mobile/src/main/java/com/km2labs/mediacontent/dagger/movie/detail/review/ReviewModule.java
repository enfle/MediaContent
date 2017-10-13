package com.km2labs.mediacontent.dagger.movie.detail.review;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.dagger.core.scope.FragmentScope;
import com.km2labs.mediacontent.dagger.core.scope.InMemoryScopeCache;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 13/10/17.
 */

@Module
public class ReviewModule {

    @Provides
    @FragmentScope
    public ReviewFragmentContract.Presenter provideReviewPresenter(MovieService movieService, @InMemoryScopeCache DataCache dataCache) {
        return new ReviewFragmentPresenter(movieService, dataCache);
    }
}