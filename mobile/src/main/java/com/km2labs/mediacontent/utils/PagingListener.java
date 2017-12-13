package com.km2labs.mediacontent.utils;


import io.reactivex.Observable;

/**
 * @author e.matsyuk
 */
public interface PagingListener<T> {
    Observable<T> onNextPage(int offset);
}