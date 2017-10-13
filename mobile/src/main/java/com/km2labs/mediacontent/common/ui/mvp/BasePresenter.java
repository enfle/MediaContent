package com.km2labs.mediacontent.common.ui.mvp;

import com.km2labs.mediacontent.common.cache.DataCache;

import java.lang.ref.WeakReference;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class BasePresenter<V extends IView> {

    private DataCache mDataCache;

    public BasePresenter(DataCache dataCache) {
        Timber.tag(getClass().getSimpleName());
        mDataCache = dataCache;
    }

    private WeakReference<V> mViewReference;

    public void onViewAttached(V view) {
        mViewReference = new WeakReference<>(view);
    }

    public void onViewDetached() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    public V getView() {
        if (mViewReference == null) {
            throw new IllegalStateException("Present is not attached to view. Call onViewAttached from activity/fragment ");
        }
        return mViewReference.get();
    }

    protected DataCache getDataCache() {
        return mDataCache;
    }

    protected <D> Observable<D> getCachedObservable(String key) {
        return mDataCache.getObservable(key);
    }
}
