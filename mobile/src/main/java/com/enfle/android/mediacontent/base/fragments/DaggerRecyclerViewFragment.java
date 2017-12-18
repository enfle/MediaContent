package com.enfle.android.mediacontent.base.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.enfle.android.mediacontent.mvp.IPresenter;
import com.enfle.android.mediacontent.mvp.IView;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


/**
 * Created by : Subham Tyagi
 * Created on :  13/09/16.
 */

public abstract class DaggerRecyclerViewFragment<V extends IView, P extends IPresenter<V>> extends RecyclerViewNetworkFragment {

    @Inject
    protected P mPresenter;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
