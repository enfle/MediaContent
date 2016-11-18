package com.km2labs.mediacontent.common.ui.mvp;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.exception.ExceptionTranslator;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class NetworkPresenter<V extends ILoadingView> extends BasePresenter<V> {

    private CompositeSubscription mSubscriptions;

    public NetworkPresenter(DataCache inMemoryCache) {
        super(inMemoryCache);
    }

    protected void addToSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    protected final void onError(Throwable throwable) {
        getView().onLoadingComplete(false);
        Timber.e(throwable);
        if (ExceptionTranslator.isNetworkException(throwable)) {
            getView().onNetworkError();
        } else {
            handleError(throwable);
        }
    }

    protected abstract void handleError(Throwable throwable);

    public void onViewAttached(V view) {
        super.onViewAttached(view);
        mSubscriptions = new CompositeSubscription();
    }

    public void onViewDetached() {
        super.onViewDetached();
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions())
            mSubscriptions.unsubscribe();
    }

    public void onDestroy() {

    }

}
