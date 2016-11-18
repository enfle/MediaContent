package com.km2labs.mediacontent.common;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.exception.ExceptionTranslator;
import com.km2labs.mediacontent.common.ui.mvp.BasePresenter;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.utils.RxUtils;

import rx.Observable;
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
        mSubscriptions = new CompositeSubscription();
        Timber.tag(getClass().getSimpleName());
    }

    protected void addToSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    protected final void onError(Throwable throwable, String tag) {
        getView().onLoadingComplete(false);
        Timber.e(throwable);
        if (ExceptionTranslator.isNetworkException(throwable)) {
            getView().onNetworkError();
        } else if (ExceptionTranslator.isAuthException(throwable)) {
            getView().onAuthenticationError();
        } else if (ExceptionTranslator.isParsingException(throwable)) {
            getView().onNetworkError();
        } else {
            handleError(tag, throwable);
        }
    }

    protected void startLoading(String tag) {
        getView().onLoadingStart();
        Subscription subscription =
                Observable.concat(getDataCache().getObservable(tag), getRemoteWithInternet(tag))
                        .compose(this::transformObservable)
                        .first(this::isCachedDataValid)
                        .flatMap(this::transformResponseData)
                        .compose(RxUtils.applyMainIOSchedulers())
                        .subscribe(o -> {
                            onRequestFinish(o, tag);
                        }, throwable -> {
                            onError(throwable, tag);
                        });
        addToSubscription(subscription);
    }

    private Observable<?> getRemoteWithInternet(String tag) {
        return NetworkHelper.isDeviceConnected().filter(aBoolean -> aBoolean)
                .flatMap(aBoolean -> getApiObservable(tag));
    }

    private <D> void onRequestFinish(D data, String tag) {
        getView().onLoadingComplete(true);
        onRequestComplete(data, tag);
    }

    protected abstract <D> void onRequestComplete(D data, String tag);

    protected Observable<?> transformObservable(Observable<?> observable) {
        return observable;
    }

    protected <D> Observable<?> transformResponseData(D data) {
        return Observable.just(data);
    }

    protected abstract <D> Boolean isCachedDataValid(D data);

    protected abstract Observable<?> getApiObservable(String tag);

    protected abstract void handleError(String tag, Throwable throwable);

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
