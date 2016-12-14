package com.km2labs.mediacontent.loaders;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.km2labs.framework.mvp.IView;
import com.km2labs.mediacontent.core.activity.BaseActivity;
import com.km2labs.framework.mvp.IPresenter;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;
import com.km2labs.mediacontent.loaders.core.PresenterLoader;

import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  03/10/16.
 */

public abstract class PresenterActivity<V extends IView, P extends IPresenter<V>> extends BaseActivity implements LoaderManager.LoaderCallbacks<P> {

    private final static int PRESENTER_LOADER_ID = 101;

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().restartLoader(PRESENTER_LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected PresenterFactory<P> getPresenterFactory() {
        return null;
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.i(getClass().getSimpleName() + "Creating Presenter");
        return new PresenterLoader<P>(this, getPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        loader.reset();
    }
}
