package com.km2labs.mediacontent.app;

import android.content.Context;

import com.km2labs.mediacontent.common.RetrofitHelper;
import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.cache.PersistenceDataCache;
import com.km2labs.mediacontent.dagger.core.scope.InMemoryScopeCache;
import com.km2labs.mediacontent.dagger.core.scope.PersistenceScopeCache;

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

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return RetrofitHelper.getRetrofitHelper().getRetrofit();
    }

    @Provides
    @Singleton
    @InMemoryScopeCache
    DataCache provideMemoryDataCache() {
        return new InMemoryCache();
    }

    @Provides
    @Singleton
    @PersistenceScopeCache
    DataCache providePersistenceCache() {
        return new PersistenceDataCache();
    }


}
