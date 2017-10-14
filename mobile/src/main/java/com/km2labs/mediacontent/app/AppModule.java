package com.km2labs.mediacontent.app;

import android.app.Application;
import android.content.Context;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.cache.PersistenceDataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.core.util.RetrofitHelper;
import com.km2labs.mediacontent.dagger.core.scope.FragmentScope;
import com.km2labs.mediacontent.dagger.core.scope.InMemory;
import com.km2labs.mediacontent.dagger.core.scope.Persistence;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return RetrofitHelper.getRetrofitHelper().getRetrofit();
    }

    @Provides
    @Singleton
    @InMemory
    DataCache provideMemoryDataCache() {
        return new InMemoryCache();
    }

    @Provides
    @Singleton
    @Persistence
    DataCache providePersistenceCache() {
        return new PersistenceDataCache();
    }

    @Provides
    @Singleton
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

}
