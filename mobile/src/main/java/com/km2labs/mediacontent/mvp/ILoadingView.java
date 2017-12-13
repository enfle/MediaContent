package com.km2labs.mediacontent.mvp;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

public interface ILoadingView extends IView {

    void onLoadingStart();

    void onLoadingComplete(boolean success);

    void showEmptyScreen();

    void onNetworkError(String tag);

    void onError();

    void onAuthenticationError(String tag);
}
