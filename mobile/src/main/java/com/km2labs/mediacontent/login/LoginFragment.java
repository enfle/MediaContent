package com.km2labs.mediacontent.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.ui.AbsNetworkFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by subhamtyagi on 16/12/16.
 */

public class LoginFragment extends AbsNetworkFragment implements LoginContract.View {

    @BindView(R.id.email)
    EditText mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.login_form)
    View mProgressView;

    @BindView(R.id.email_login_form)
    View mLoginFormView;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected final int getLayoutView() {
        return R.layout.login_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        mPresenter.performLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onEmptyEmail() {

    }

    @Override
    public void onEmptyPassword() {

    }

    @Override
    public void onError(Throwable error) {

    }
}
