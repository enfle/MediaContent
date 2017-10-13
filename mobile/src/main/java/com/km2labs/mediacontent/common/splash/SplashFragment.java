package com.km2labs.mediacontent.common.splash;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.km2labs.mediacontent.dagger.core.ui.fragment.BaseLoadingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseLoadingFragment<SplashFragmentContract.View, SplashFragmentContract.Presenter>
        implements SplashFragmentContract.View {


    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void loadData() {
        mPresenter.performGuestLogin();
    }



    @Override
    public void onError() {

    }
}
