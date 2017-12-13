package com.km2labs.mediacontent.splash;

import com.km2labs.mediacontent.mvp.ILoadingView;
import com.km2labs.mediacontent.mvp.INetworkPresenter;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface SplashFragmentContract {

    interface Presenter extends INetworkPresenter<View> {

        void performGuestLogin();
    }

    interface View extends ILoadingView {

    }
}
