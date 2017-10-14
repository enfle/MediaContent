package com.km2labs.mediacontent.common.ui;

import android.support.design.widget.Snackbar;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.dagger.core.ui.fragment.BaseLoadingFragment;

/**
 * Created by subhamtyagi on 16/12/16.
 */

public abstract class AbsNetworkFragment extends BaseLoadingFragment {

    protected  void onRetry(){

    }

    public void onRequestStart() {
        onLoadingStart();
    }

    public void onRequestComplete(boolean isSuccess) {
        onLoadingComplete(isSuccess);
    }

    public void onNetworkError(String tag) {
        Snackbar.make(getRootView(), R.string.error_internet_connection, Snackbar.LENGTH_LONG).setAction(R.string.retry, v -> onRetry()).show();
    }

    public void onAuthenticationError(String tag) {
    }
}
