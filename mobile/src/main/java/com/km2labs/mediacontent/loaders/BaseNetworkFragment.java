package com.km2labs.mediacontent.loaders;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.km2labs.mediacontent.R;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.framework.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.views.DotsView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.km2labs.mediacontent.R.id.message;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseNetworkFragment<V extends ILoadingView, P extends INetworkPresenter<V>>
        extends PresenterFragment<V, P> implements SwipeRefreshLayout.OnRefreshListener {

    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.progress_bar)
    DotsView mProgressBar;

    @BindView(message)
    TextView mMessageTextView;

    @BindView(R.id.retry_button)
    Button mRetryButton;

    protected View mRootView;

    @LayoutRes
    protected abstract int getContentLayoutResId();

    protected abstract void loadData();

    @Override
    protected final View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.loading_fragment, container, false);
        View contentView = inflater.inflate(getContentLayoutResId(), container, false);

        if (contentView == null) {
            return viewGroup;
        }

        if (enablePullToRefresh()) {
            setRefreshLayout(contentView, viewGroup);
        } else {
            viewGroup.addView(contentView);
        }
        mRootView = viewGroup;
        return mRootView;
    }

    private void setRefreshLayout(View contentView, ViewGroup viewGroup) {
        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(viewGroup.getLayoutParams());
        swipeRefreshLayout.setLayoutParams(layoutParams);
        viewGroup.addView(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.addView(contentView);
    }

    public void onLoadingStart() {
        ObjectAnimator dotsAnimator = ObjectAnimator.ofFloat(mProgressBar, DotsView.DOTS_PROGRESS, 0, 1f);
        dotsAnimator.setDuration(900);
        dotsAnimator.setRepeatMode(ValueAnimator.REVERSE);
        dotsAnimator.setRepeatCount(ValueAnimator.INFINITE);
        dotsAnimator.setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR);
        dotsAnimator.start();
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setCurrentProgress(0);
    }

    public void onLoadingComplete(boolean isSuccess) {
        onLoadingComplete(isSuccess, null);
    }

    private void onLoadingComplete(boolean isSuccess, String requestTag) {
        mProgressBar.setVisibility(View.GONE);
        mRetryButton.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        mMessageTextView.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        mRetryButton.setTag(requestTag);
    }

    public void setMessage(@StringRes int id) {
        mMessageTextView.setVisibility(View.VISIBLE);
        mMessageTextView.setText(id);
    }

    @OnClick(R.id.retry_button)
    protected void onRetryClicked(View view) {
        if (view != null) {
            String requestTag = (String) view.getTag();
            if (!TextUtils.isEmpty(requestTag)) {
                mPresenter.startRequest(requestTag);
            } else {
                loadData();
            }
        }

    }


    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        onLoadingStart();
        loadData();
    }

    protected boolean enablePullToRefresh() {
        return false;
    }


    public void showEmptyScreen() {
        mMessageTextView.setText(R.string.no_item_exists);
        mMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {

    }

    public void onAuthenticationError(String tag) {
        Snackbar.make(mRootView, R.string.error_user_uthenticaton_failed, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.login, this::onLoginClicked)
                .show();
    }

    private void onLoginClicked(View view) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra(LoginActivity.LAUNCHER_ACTIVITY_NAME, getActivity().getClass().getName());
        startActivity(intent);

    }

    public void onNetworkError(String tag) {
        setMessage(R.string.request_failed);
        onLoadingComplete(false);
    }
}
