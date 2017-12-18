package com.enfle.android.mediacontent.splash;


import android.support.v4.app.Fragment;

import com.enfle.android.mediacontent.base.fragments.BaseNetworkFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseNetworkFragment
        implements SplashFragmentContract.View {

    @Override
    protected int getContentLayoutResId() {
        return 0;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onError() {

    }
}
