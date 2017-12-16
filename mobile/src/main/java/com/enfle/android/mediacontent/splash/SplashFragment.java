package com.enfle.android.mediacontent.splash;


import android.support.v4.app.Fragment;

import com.enfle.android.mediacontent.base.fragments.BaseNetworkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseNetworkFragment<SplashFragmentContract.View, SplashFragmentContract.Presenter>
        implements SplashFragmentContract.View {

    @Override
    protected int getContentLayoutResId() {
        return 0;
    }

    @Override
    protected void loadData() {
        mPresenter.performGuestLogin();
    }


    @Override
    public void onError() {

    }
}
