package com.km2labs.mediacontent.core.mvp.presenter;

import com.km2labs.mediacontent.core.util.NetworkHelper;
import com.km2labs.mediacontent.core.exception.ExceptionTranslator;
import com.km2labs.mediacontent.core.mvp.view.INetworkView;
import com.km2labs.mediacontent.core.util.RxUtils;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class AbsNetworkPresenter<V extends INetworkView> extends AbsPresenter<V> {

    private CompositeSubscription mSubscriptions;

    public AbsNetworkPresenter() {
        Timber.tag(getClass().getSimpleName());
        mSubscriptions = new CompositeSubscription();
    }

    protected abstract <D> void onRequestComplete(D data, String tag);

    protected <D> Observable<?> transformResponseData(D data) {
        return Observable.just(data);
    }

    protected abstract <D> Boolean isCachedDataValid(D data);

    protected abstract Observable<?> getApiObservable(String tag);

    protected abstract void handleError(String tag, Throwable throwable);

    protected void addToSubscription(final Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    protected final void onError(final Throwable throwable, final String tag) {
        getView().onRequestComplete(false);
        Timber.e(throwable);
        if (ExceptionTranslator.isNetworkException(throwable)) {
            getView().onNetworkError(tag);
        } else if (ExceptionTranslator.isAuthException(throwable)) {
            getView().onAuthenticationError(tag);
        } else if (ExceptionTranslator.isParsingException(throwable)) {
            getView().onNetworkError(tag);
        } else {
            handleError(tag, throwable);
        }
    }

    public void startRequest(String tag) {
        getView().onRequestStart();
        Subscription subscription =
                Observable.concat(getDataCache().getObservable(tag), getRemoteWithInternet(tag))
                        .first(this::isCachedDataValid)
                        .flatMap(this::transformResponseData)
                        .compose(RxUtils.applyMainIOSchedulers())
                        .subscribe(o -> onRequestFinish(o, tag), throwable -> onError(throwable, tag));
        addToSubscription(subscription);
    }

    public void startRequest() {
        startRequest("DefaultTag");
    }

    public void stopRequest(String requestTag) {

    }

    public void onDestroy() {

    }

    private Observable<?> getRemoteWithInternet(String tag) {
        return NetworkHelper.isDeviceConnected().filter(aBoolean -> aBoolean)
                .flatMap(aBoolean -> getApiObservable(tag));
    }

    private <D> void onRequestFinish(D data, String tag) {
        getView().onRequestComplete(true);
        onRequestComplete(data, tag);
    }

    public void onViewAttached(V view) {
        super.onViewAttached(view);
        mSubscriptions = new CompositeSubscription();
    }

    public void onViewDetached() {
        super.onViewDetached();
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions())
            mSubscriptions.unsubscribe();
    }
}
