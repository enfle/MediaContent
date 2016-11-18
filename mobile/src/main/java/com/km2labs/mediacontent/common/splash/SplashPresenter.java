package com.km2labs.mediacontent.common.splash;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.ui.mvp.NetworkPresenter;
import com.km2labs.mediacontent.common.user.UserSettings;
import com.km2labs.mediacontent.common.utils.RxUtils;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class SplashPresenter extends NetworkPresenter<SplashFragmentContract.View> implements SplashFragmentContract.Presenter {

    private UserSettings mUserSettings;
    private UserAuthenticationService mUserAuthenticationService;

    public SplashPresenter(UserSettings userSettings, UserAuthenticationService userAuthenticationService, DataCache dataCache) {
        super(dataCache);
        mUserSettings = userSettings;
        mUserAuthenticationService = userAuthenticationService;
    }

    @Override
    public void performGuestLogin() {
        mUserAuthenticationService.guestLogin()
                .compose(RxUtils.applyMainIOSchedulers())
                .doOnNext(guestUserLoginResponseDTO -> {
                    mUserSettings.getUser();
                })
                .subscribe(guestUserLoginResponseDTO -> {
                }, this::onError);
    }

    @Override
    protected void handleError(Throwable throwable) {

    }
}
