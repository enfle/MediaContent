package com.km2labs.mediacontent.common.cache;

import com.km2labs.mediacontent.dagger.core.scope.Persistence;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */
@Persistence
public class PersistenceDataCache implements DataCache {

    @Inject
    public PersistenceDataCache() {
    }

    @Override
    public <T> void put(String key, T data) {
    }

    @Override
    public <T> String put(T data) {
        return null;
    }

    @Override
    public <T> Observable<T> getObservable(String key) {
        return null;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public <T> T retrieve(String key) {
        return null;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }


}
