package com.km2labs.mediacontent.loaders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.km2labs.mediacontent.common.ui.BaseFragment;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;
import com.km2labs.mediacontent.common.ui.mvp.IView;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;
import com.km2labs.mediacontent.loaders.core.PresenterLoader;

import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  03/10/16.
 */

public abstract class PresenterFragment<V extends IView, P extends IPresenter<V>> extends BaseFragment implements LoaderManager.LoaderCallbacks<P> {

    private final static int PRESENTER_LOADER_ID = 102;

    protected P mPresenter;
    private boolean isPresenterCrated;

    protected abstract PresenterFactory<P> getPresenterFactory();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(getLoaderId(), null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.i("%s Creating Presenter", getClass().getSimpleName());
        return new PresenterLoader<>(getActivity(), getPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        Timber.i("%s Presenter created", getClass().getSimpleName());
        mPresenter = data;
        if (!isPresenterCrated) {
            isPresenterCrated = true;
            onPresenterCreated();
        }
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        Timber.i(" %s Destroying Presenter", getClass().getSimpleName());
        isPresenterCrated = false;
        onPresenterDestroy();
        mPresenter = null;
        loader.reset();
    }

    protected void onPresenterCreated() {
        if (mPresenter != null)
            mPresenter.onViewAttached(getMVPView());
    }

    protected void onPresenterDestroy() {

    }

    protected int getLoaderId() {
        return PRESENTER_LOADER_ID;
    }

    protected V getMVPView() {
        return (V) this;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.onViewDetached();
    }
}
