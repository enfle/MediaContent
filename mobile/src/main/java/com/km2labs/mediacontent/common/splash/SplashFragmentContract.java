package com.km2labs.mediacontent.common.splash;

import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface SplashFragmentContract {

    interface Presenter extends IPresenter<View> {

        void performGuestLogin();
    }

    interface View extends ILoadingView {

    }
}
