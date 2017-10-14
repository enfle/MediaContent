package com.km2labs.mediacontent.core.mvp.view;

/**
 * Created by subhamtyagi on 16/12/16.
 */

public interface INetworkView extends IView {

    /**
     * Will be called when loading of data starts.
     */
    void onRequestStart();

    /**
     * Will be called when data request completed.
     *
     * @param success -true of request was successful other wise false.
     */
    void onRequestComplete(boolean success);


    /**
     * Will be called from presenter if request failed due to network issues.
     *
     * @param tag -constant which was used start loading request(see
     *            {@link com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter
     *            #startRequest(String)}).
     */
    void onNetworkError(String tag);

    /**
     * Will be called from presenter if unknown error received
     * while executing request.
     */
    void onError(Throwable error);

    /**
     * Will be called from presenter when request
     * return with authentication fail.
     *
     * @param tag -constant which was used start loading
     *            request(see {@link com.km2labs.mediacontent.common
     *            .AbsNetworkPresenter#startRequest(String)}).
     */
    void onAuthenticationError(String tag);
}
