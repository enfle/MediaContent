package com.enfle.android.mediacontent.mvp;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface INetworkPresenter<V extends IView> extends IPresenter<V> {

    void startRequest();

    void startRequest(String requestTag);

    void stopRequest(String requestTag);

}
