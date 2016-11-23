package com.km2labs.mediacontent.common.splash;


import android.support.v4.app.Fragment;

import com.km2labs.mediacontent.loaders.BaseNetworkFragment;
import com.km2labs.mediacontent.loaders.DefaultPresenterFactory;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

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
    protected SplashFragmentContract.View getMVPView() {
        return this;
    }

    @Override
    protected PresenterFactory<SplashFragmentContract.Presenter> getPresenterFactory() {
        return new DefaultPresenterFactory<>(DefaultPresenterFactory.TYPE_MOVIE_LIST);
    }


    @Override
    public void onError() {

    }
}
