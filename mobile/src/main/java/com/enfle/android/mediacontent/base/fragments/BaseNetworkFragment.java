package com.enfle.android.mediacontent.base.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.views.DotsView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

import static com.enfle.android.mediacontent.R.id.message;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseNetworkFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected View mRootView;

    @BindView(R.id.progress_bar)
    @Nullable
    DotsView mProgressBar;

    @BindView(message)
    @Nullable
    TextView mMessageTextView;

    @BindView(R.id.retry_button)
    @Nullable
    Button mRetryButton;

    /**
     * Abstraction method how content view will be rendered
     *
     * @return - should return content view layout id
     */
    @LayoutRes
    protected abstract int getContentLayoutResId();

    /**
     * Will be called when fragment is ready to load data
     */
    protected abstract void loadData();

    /**
     * enable/disable pull to refresh
     *
     * @return true -if pull to refresh is enabled otherwise false
     */
    protected boolean enablePullToRefresh() {
        return false;
    }

    /**
     * Will be called when pull to refresh action triggered
     */
    @Override
    public void onRefresh() {
    }

    /**
     * Handle authentication error. Will be called by presenter when some auth failed
     * i.e no internet connection
     */
    public void onAuthenticationError(String tag) {
        mMessageTextView.setVisibility(View.GONE);
        Snackbar.make(mRootView, R.string.error_user_uthenticaton_failed, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.login, v -> onLoginClicked())
                .show();
    }

    /**
     * Handle all type network error. Will be called by presenter when some network error happened
     * i.e no internet connection
     */
    public void onNetworkError(String tag) {
        setMessage(R.string.request_failed);
        onLoadingComplete(false);
    }

    public void showEmptyScreen() {
        mMessageTextView.setText(R.string.no_item_exists);
        mMessageTextView.setVisibility(View.VISIBLE);
    }

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
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
        if (mProgressBar != null) {
            mProgressBar.start();
        }
    }

    public void onLoadingComplete(boolean isSuccess) {
        onLoadingComplete(isSuccess, null);
    }

    private void onLoadingComplete(boolean isSuccess, String requestTag) {
        if (mProgressBar != null) {
            mProgressBar.stop();
            mProgressBar.setVisibility(View.GONE);
        }

        if (mMessageTextView != null) {
            mMessageTextView.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        }

        if (mRetryButton != null) {
            mRetryButton.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
            mRetryButton.setTag(requestTag);
        }
    }

    public void setMessage(@StringRes int id) {
        if (mMessageTextView != null) {
            mMessageTextView.setVisibility(View.VISIBLE);
            mMessageTextView.setText(id);
        }
    }

    @Optional
    @OnClick(R.id.retry_button)
    protected void onRetryClicked(View view) {
        if (view == null) {
            loadData();
            return;
        }

        String requestTag = (String) view.getTag();
        if (TextUtils.isEmpty(requestTag)) {
            loadData();
        } else {
            // TODO Handle retry
            //mPresenter.startRequest(requestTag);
        }
    }

    private void onLoginClicked() {
        // TODO: 17/12/17 Handle login flow
    }
}
