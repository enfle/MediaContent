package com.km2labs.mediacontent.cache;


import io.reactivex.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

public interface DataCache {

    /**
     * Put value in cache
     *
     * @param key - String value.
     * @param <T> - Type if data ;
     * @return data
     */
    <T> void put(String key, T data);

    /**
     * Put value in cache
     *
     * @param <T> - Type if data.
     * @return - key , will be needed to retrieved the value.
     */
    <T> String put(T data);

    /**
     * Retrieve value from cache .
     *
     * @param key - String value
     * @param <T> - Type if data will be retrieved from cache
     * @return Observable
     */
    <T> Observable<T> getObservable(String key);

    /**
     * Retrieve value from cache .
     *
     * @param key - String value
     * @param <T> - Type if data will be retrieved from cache
     * @return Observable
     */
    <T> T get(String key);

    /**
     * Retrieve value from cache and remove it from cache.
     *
     * @param key - String value
     * @param <T> - Type if data will be retrieved from cache
     * @return data
     */
    <T> T retrieve(String key);

    boolean contains(String key);
}
