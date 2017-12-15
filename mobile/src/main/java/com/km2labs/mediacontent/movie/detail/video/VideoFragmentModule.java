package com.km2labs.mediacontent.movie.detail.video;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.dagger.scope.InMemoryCache;
import com.km2labs.mediacontent.dagger.scope.PerFragment;
import com.km2labs.mediacontent.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 13/12/17.
 */

@Module
public class VideoFragmentModule {

    @Provides
    @PerFragment
    public VideoFragmentContract.Presenter providesPresenter(MovieService movieService, @InMemoryCache DataCache dataCache) {
        return new VideoListPresenter(movieService, dataCache);
    }
}
