package com.km2labs.mediacontent.loaders.core;

import android.content.Context;
import android.support.v4.content.Loader;

import com.km2labs.framework.mvp.IPresenter;

/**
 * Created by : Subham Tyagi
 * Created on :  03/10/16.
 */

public class PresenterLoader<P extends IPresenter> extends Loader<P> {

    private P mPresenter;

    private PresenterFactory<P> mPresenterFactory;


    public PresenterLoader(Context context, PresenterFactory<P> presenterFactory) {
        super(context);
        mPresenterFactory = presenterFactory;
    }

    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        if (mPresenterFactory != null)
            mPresenter = mPresenterFactory.create();// create new presenter

        if (mPresenter != null)
            deliverResult(mPresenter);
    }


    @Override
    protected void onReset() {

        if (mPresenter != null)
            mPresenter.onDestroy();

        mPresenter = null;
    }
}
