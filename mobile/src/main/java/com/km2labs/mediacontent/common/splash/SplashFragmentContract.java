package com.km2labs.mediacontent.common.splash;

import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.framework.mvp.ILoadingView;

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
