package com.enfle.android.mediacontent.splash;

import com.enfle.android.mediacontent.mvp.ILoadingView;
import com.enfle.android.mediacontent.mvp.INetworkPresenter;

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
