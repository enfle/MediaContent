package com.enfle.android.mediacontent.splash;

import com.enfle.android.mediacontent.cache.DataCache;
import com.enfle.android.mediacontent.mvp.BaseNetworkPresenter;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class SplashPresenter extends BaseNetworkPresenter<SplashFragmentContract.View> implements SplashFragmentContract.Presenter {


    public SplashPresenter(DataCache inMemoryCache) {
        super(inMemoryCache);
    }

    @Override
    public void performGuestLogin() {
        startRequest();
    }


    @Override
    protected <D> void onRequestComplete(D data, String tag) {

    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return null;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return null;
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }
}
