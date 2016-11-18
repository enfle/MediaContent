package com.km2labs.mediacontent.dagger.movie;

import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityModule;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

@Module
public abstract class MovieModule<A> extends ActivityModule<A> {

    public MovieModule(A activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }
}
