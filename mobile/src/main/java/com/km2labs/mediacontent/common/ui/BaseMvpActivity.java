package com.km2labs.mediacontent.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.km2labs.mediacontent.core.mvp.presenter.BasePresenter;
import com.km2labs.mediacontent.core.mvp.view.IView;
import com.km2labs.mediacontent.dagger.core.ui.activity.DaggerActivity;

import javax.inject.Inject;

/**
 * Created by subhamtyagi on 15/12/16.
 */

public abstract class BaseMvpActivity<V extends IView, P extends BasePresenter<V>> extends DaggerActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onViewAttached((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDetached();
    }

    public void onEmptyResult() {

    }

    public void onNetworkError(String tag) {
       /* Snackbar snackbar = Snackbar
                .make(this, "Message is deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        snackbar.show();*/
    }

    public void onAuthenticationError(String tag) {
       /* Snackbar snackbar1 = Snackbar.make(ge, "Message is restored!", Snackbar.LENGTH_SHORT);
        snackbar1.show();*/
    }
}
