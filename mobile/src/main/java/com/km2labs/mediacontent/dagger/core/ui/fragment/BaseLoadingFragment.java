package com.km2labs.mediacontent.dagger.core.ui.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.ui.AbsNetworkFragment;
import com.km2labs.mediacontent.common.ui.views.DotsView;
import com.km2labs.mediacontent.core.fragment.BaseFragment;
import com.km2labs.mediacontent.core.mvp.view.ILoadingView;
import com.km2labs.mediacontent.core.mvp.presenter.NetworkPresenter;

import butterknife.BindView;
import butterknife.Optional;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseLoadingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.progress_bar)
    DotsView mProgressBar;

    @BindView(R.id.message)
    TextView mMessageTextView;

    @BindView(R.id.retry_button)
    Button mRetryButton;

    @BindView(R.id.contentFrame)
    @Nullable
    View mContentView;

    @BindView(R.id.swipe_referesh_layout)
    @Nullable
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected void loadData(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefreshLayout();
    }

    private void setRefreshLayout() {
        if (mSwipeRefreshLayout != null && enablePullToRefresh())
            mSwipeRefreshLayout.setOnRefreshListener(this);
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
        mRetryButton.setVisibility(isSuccess ? View.GONE : View.GONE);
        mMessageTextView.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
    }

    public void setMessage(@StringRes int id) {
        mMessageTextView.setVisibility(View.VISIBLE);
        mMessageTextView.setText(id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    protected boolean enablePullToRefresh() {
        return false;
    }

    public void onEmptyResult() {
        // FIXME: 13/09/16 create string resource
        mMessageTextView.setText("No item exists");
    }

    @Override
    public void onRefresh() {

    }
}
