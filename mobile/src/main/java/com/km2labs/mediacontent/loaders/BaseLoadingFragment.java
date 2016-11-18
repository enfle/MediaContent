package com.km2labs.mediacontent.loaders;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.ui.mvp.ILoadingView;
import com.km2labs.mediacontent.common.ui.mvp.IPresenter;
import com.km2labs.mediacontent.common.ui.views.DotsView;

import butterknife.BindView;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseLoadingFragment<V extends ILoadingView, P extends IPresenter<V>>
        extends PresenterFragment<V, P> implements SwipeRefreshLayout.OnRefreshListener {

    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.progress_bar)
    DotsView mProgressBar;

    @BindView(R.id.message)
    TextView mMessageTextView;

    @BindView(R.id.retry_button)
    Button mRetryButton;

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
        return viewGroup;
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
        mProgressBar.setVisibility(View.GONE);
        mRetryButton.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        mMessageTextView.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
    }

    public void setMessage(@StringRes int id) {
        mMessageTextView.setVisibility(View.VISIBLE);
        mMessageTextView.setText(id);
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
        // FIXME: 13/09/16 create string resource
        mMessageTextView.setText("No item exists");
        mMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {

    }

    public void onAuthenticationError() {

    }

    public void onNetworkError() {
        setMessage(R.string.request_failed);
    }
}
