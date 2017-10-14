package com.km2labs.mediacontent.splash;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter;
import com.km2labs.mediacontent.common.user.UserSettings;
import com.km2labs.mediacontent.core.mvp.presenter.BasePresenter;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private UserSettings mUserSettings;

    public SplashPresenter(UserSettings userSettings, DataCache dataCache) {
        mUserSettings = userSettings;
    }


    @Override
    public void onViewAttached(SplashContract.View view) {

    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public SplashContract.View getView() {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
