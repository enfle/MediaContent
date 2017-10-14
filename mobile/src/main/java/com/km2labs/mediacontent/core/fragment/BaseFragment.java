package com.km2labs.mediacontent.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.cache.InMemoryCache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;

    private DataCache mDataCache;

    private View mRootView;

    protected abstract int getLayoutView();

    protected void setRetainedValue(String key, Object value) {
        mDataCache.put(key, value);
    }

    protected <T> T getRetainedValue(String key) {
        return mDataCache.get(key);
    }

    protected boolean contains(String key) {
        return mDataCache.contains(key);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataCache = new InMemoryCache();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutView(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    public View getRootView() {
        return mRootView;
    }
}
