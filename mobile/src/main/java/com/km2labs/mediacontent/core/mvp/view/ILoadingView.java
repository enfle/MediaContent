package com.km2labs.mediacontent.core.mvp.view;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

public interface ILoadingView extends INetworkView {
    /**
     * Will be called when loading of data starts.
     */
    void onLoadingStart();

    /**
     * Will be called when data request completed.
     *
     * @param success -true of request was successful other wise false.
     */
    void onLoadingComplete(boolean success);

    /**
     * Will we called if presenter receive empty result.
     */
    void onEmptyResult();

    /**
     * Will be called from presenter if request failed due to network issues.
     *
     * @param tag -constant which was used start loading request(see
     *            {@link com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter
     *            #startRequest(String)}).
     */
    void onNetworkError(String tag);


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
