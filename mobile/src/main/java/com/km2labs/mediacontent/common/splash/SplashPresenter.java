package com.km2labs.mediacontent.common.splash;

import com.km2labs.framework.cache.DataCache;
import com.km2labs.framework.network.BaseNetworkPresenter;
import com.km2labs.mediacontent.common.user.UserSettings;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class SplashPresenter extends BaseNetworkPresenter<SplashFragmentContract.View> implements SplashFragmentContract.Presenter {

    private UserSettings mUserSettings;

    private UserAuthenticationService mUserAuthenticationService;

    public SplashPresenter(UserSettings userSettings, UserAuthenticationService userAuthenticationService, DataCache dataCache) {
        super(dataCache);
        mUserSettings = userSettings;
        mUserAuthenticationService = userAuthenticationService;
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
        return mUserAuthenticationService.guestLogin();
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }
}
