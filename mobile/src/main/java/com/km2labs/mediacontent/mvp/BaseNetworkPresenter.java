package com.km2labs.mediacontent.mvp;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.network.NetworkHelper;
import com.km2labs.mediacontent.utils.ExceptionTranslator;
import com.km2labs.mediacontent.utils.RxUtils;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public abstract class BaseNetworkPresenter<V extends ILoadingView> extends BasePresenter<V> {

    private ArrayCompositeSubscription mSubscriptions;

    public BaseNetworkPresenter(DataCache inMemoryCache) {
        super(inMemoryCache);
        mSubscriptions = new ArrayCompositeSubscription(5);
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

    protected void addToSubscription(Subscription subscription) {
        mSubscriptions.setResource(0, subscription);
    }

    protected final void onError(Throwable throwable, String tag) {
        getView().onLoadingComplete(false);
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
        getView().onLoadingStart();
        getRemoteWithInternet(tag)
                //.compose(this::transformObservable)
                .flatMap((Function<Object, ObservableSource<?>>) o -> transformResponseData(o))
                .compose(RxUtils.applyMainIOSchedulers())
                .subscribe(o -> onRequestFinish(o, tag), throwable -> onError(throwable, tag));
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
        getView().onLoadingComplete(true);
        onRequestComplete(data, tag);
    }

    public void onViewAttached(V view) {
        super.onViewAttached(view);
    }

    public void onViewDetached() {
        super.onViewDetached();
    }
}
