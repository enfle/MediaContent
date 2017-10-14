package com.km2labs.mediacontent.login;

import android.text.TextUtils;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter;
import com.km2labs.mediacontent.core.util.RxUtils;
import com.km2labs.mediacontent.dagger.core.scope.InMemory;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by subhamtyagi on 14/12/16.
 */

public class LoginPresenter extends AbsNetworkPresenter<LoginContract.View> implements LoginContract.Presenter {

    LoginService mLoginService;

    public LoginPresenter(LoginService loginService) {
        mLoginService = loginService;
    }

    @Override
    protected void onRequestComplete(Object data, String tag) {
        Timber.d("onRequest Complete %s", data.toString());
    }

    @Override
    protected Boolean isCachedDataValid(Object data) {
        return false;
    }

    @Override
    protected Observable<?> getApiObservable(String tag) {
        return mLoginService.getRequestToken()
                .flatMap(requestTokenDTO -> mLoginService.validateRequestToken("", "", requestTokenDTO.getRequestToken()))
                .flatMap(validationResultDto -> mLoginService.createSession(validationResultDto.getRequestToken()));
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {

    }

    public void performLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            getView().onEmptyEmail();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getView().onEmptyPassword();
            return;
        }
        startRequest();
    }

    @Override
    public void retry(String tag) {

    }
}
