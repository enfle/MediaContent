package com.km2labs.mediacontent.login;

import com.km2labs.mediacontent.core.mvp.view.INetworkView;
import com.km2labs.mediacontent.core.mvp.presenter.NetworkPresenter;

/**
 * Created by subhamtyagi on 14/12/16.
 */

public interface LoginContract {

    interface View extends INetworkView {
        /**
         * Will be called when UserId/email is empty
         */
        void onEmptyEmail();

        /**
         * Will be called when password id empty
         */
        void onEmptyPassword();
    }

    interface Presenter extends NetworkPresenter<View> {
        void performLogin(String userName, String password);
    }

}
