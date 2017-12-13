package com.km2labs.mediacontent.mvp;

import com.km2labs.mediacontent.cache.DataCache;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class BasePresenter<V extends IView> {

    private DataCache mDataCache;
    private WeakReference<V> mViewReference;

    public BasePresenter(DataCache dataCache) {
        Timber.tag(getClass().getSimpleName());
        mDataCache = dataCache;
    }

    protected void onViewAttached(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    protected void onViewDetached() {
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
