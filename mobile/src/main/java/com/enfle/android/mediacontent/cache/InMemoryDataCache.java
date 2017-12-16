package com.enfle.android.mediacontent.cache;

import android.text.TextUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

public class InMemoryDataCache implements DataCache {

    private final AtomicLong NEXT_ID = new AtomicLong(100L);
    private ConcurrentHashMap<String, Object> mCache = new ConcurrentHashMap<>();

    public InMemoryDataCache() {
    }

    @Override
    public <T> void put(String key, T data) {
        if (data == null || TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("data or key cannot be null");
        }
        mCache.put(key, data);
    }

    @Override
    public <T> String put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data or key cannot be null");
        }
        String key = String.valueOf(NEXT_ID.get());
        mCache.put(key, data);
        return key;
    }

    @Override
    public <T> Observable<T> getObservable(String key) {
        return Observable.just((T) mCache.get(key));
    }

    @Override
    public <T> T get(String key) {
        return (T) mCache.get(key);
    }

    @Override
    public <T> T retrieve(String key) {
        return (T) mCache.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return false;
    }
}
