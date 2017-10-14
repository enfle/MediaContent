package com.km2labs.mediacontent.core.mvp.presenter;

import com.km2labs.mediacontent.core.mvp.view.IView;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface BasePresenter<V extends IView> {

    void onViewAttached(V view);

    void onViewDetached();

    V getView();

    void onDestroy();
}
