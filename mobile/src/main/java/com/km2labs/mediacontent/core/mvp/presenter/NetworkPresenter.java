package com.km2labs.mediacontent.core.mvp.presenter;

import com.km2labs.mediacontent.core.mvp.view.INetworkView;

/**
 * Created by subhamtyagi on 16/12/16.
 */

public interface NetworkPresenter<V extends INetworkView> extends BasePresenter<V> {

    void startRequest();

    void startRequest(final String tag);

    void retry(final String tag);
}
