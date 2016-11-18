package com.km2labs.mediacontent.common.ui.mvp;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface IPresenter<V extends IView> {

    void onViewAttached(V view);

    void onViewDetached();

    V getView();

    void onDestroy();
}
