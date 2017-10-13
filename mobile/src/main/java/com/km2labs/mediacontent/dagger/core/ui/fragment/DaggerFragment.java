package com.km2labs.mediacontent.dagger.core.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.km2labs.mediacontent.common.ui.BaseFragment;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;
import com.km2labs.mediacontent.common.ui.mvp.IView;
import com.km2labs.mediacontent.dagger.core.scope.FragmentScope;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by : Subham Tyagi
 * Created on :  13/09/16.
 */

public abstract class DaggerFragment<V extends IView, P extends IPresenter<V>> extends BaseFragment {

    @Inject
    @FragmentScope
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
