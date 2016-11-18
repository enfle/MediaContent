package com.km2labs.mediacontent.common.utils;

import rx.Observable;

/**
 * @author e.matsyuk
 */
public interface PagingListener<T> {
    Observable<T> onNextPage(int offset);
}