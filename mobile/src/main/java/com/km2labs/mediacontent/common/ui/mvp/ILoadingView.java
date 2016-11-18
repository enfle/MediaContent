package com.km2labs.mediacontent.common.ui.mvp;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

public interface ILoadingView extends IView {

    void onLoadingStart();

    void onLoadingComplete(boolean success);

    void showEmptyScreen();

    void onNetworkError();

    void onError();

    void onAuthenticationError();
}
