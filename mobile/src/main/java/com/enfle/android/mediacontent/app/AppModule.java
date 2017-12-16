package com.enfle.android.mediacontent.app;

import android.content.Context;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.cache.InMemoryDataCache;
import com.enfle.android.mediacontent.cache.PersistenceDataCache;
import com.enfle.android.mediacontent.dagger.ActivityModule;
import com.enfle.android.mediacontent.dagger.scope.InMemoryCache;
import com.enfle.android.mediacontent.dagger.scope.PersistenceCache;
import com.enfle.android.mediacontent.network.RetrofitHelper;
import com.enfle.android.mediacontent.service.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@Module(includes = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class}
)
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(App app) {
        return app;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return RetrofitHelper.getRetrofitHelper().getRetrofit();
    }

    @Provides
    @Singleton
    @InMemoryCache
    DataCache provideMemoryDataCache() {
        return new InMemoryDataCache();
    }

    @Provides
    @Singleton
    @PersistenceCache
    DataCache providePersistenceCache() {
        return new PersistenceDataCache();
    }

    @Provides
    @Singleton
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }
}
