package com.km2labs.mediacontent.loaders.movie;

import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.dagger.core.scope.PersistentScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

@Module
public class MovieModule {
    @Provides
    @PersistentScope
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }
}
