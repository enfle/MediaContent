package com.km2labs.mediacontent.dagger.core.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.km2labs.mediacontent.app.App;
import com.km2labs.mediacontent.common.ui.BaseFragment;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;
import com.km2labs.mediacontent.common.ui.mvp.IView;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivitySubcomponentBuilders;

import javax.inject.Inject;

/**
 * Created by : Subham Tyagi
 * Created on :  13/09/16.
 */

public abstract class DaggerFragment<V extends IView, P extends IPresenter<V>> extends BaseFragment {

    @Inject
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupActivityComponent();
    }

    protected void setupActivityComponent() {
        injectMembers(App.get(getContext()));
    }

    protected abstract void injectMembers(ActivitySubcomponentBuilders activitySubcomponentBuilders);

}
