package com.enfle.android.mediacontent.movie.detail.reviews;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.dagger.scope.InMemoryCache;
import com.enfle.android.mediacontent.dagger.scope.PerFragment;
import com.enfle.android.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 13/12/17.
 */

@Module
public class ReviewFragmentModule {

    @Provides
    @PerFragment
    public ReviewFragmentContract.Presenter providesPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new ReviewFragmentPresenter(movieService, dataCache);
    }
}
