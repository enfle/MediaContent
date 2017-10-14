package com.km2labs.mediacontent.core.mvp.presenter;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.core.mvp.view.IView;
import com.km2labs.mediacontent.dagger.core.scope.InMemory;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class AbsPresenter<V extends IView> {

    @Inject
    @InMemory
    protected DataCache mDataCache;

    public AbsPresenter() {
        Timber.tag(getClass().getSimpleName());
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
            throw new IllegalStateException("Present is not attached to view. Call onViewAttached from root/fragment ");
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
