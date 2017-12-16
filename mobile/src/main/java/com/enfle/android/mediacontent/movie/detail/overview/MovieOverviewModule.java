package com.enfle.android.mediacontent.movie.detail.overview;

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
public class MovieOverviewModule {

    @Provides
    @PerFragment
    OverviewFragmentContract.Presenter presenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new OverviewFragmentPresenter(movieService, dataCache);
    }
}
