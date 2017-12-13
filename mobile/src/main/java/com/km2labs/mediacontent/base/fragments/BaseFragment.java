package com.km2labs.mediacontent.base.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.cache.InMemoryDataCache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;

    private DataCache mDataCache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataCache = new InMemoryDataCache();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getFragmentView(inflater, container);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected String setRetainedValue(Object value) {
        return mDataCache.put(value);
    }

    protected <T> T getRatainedValue(String key) {
        return mDataCache.get(key);
    }

    protected boolean contains(String key) {
        return mDataCache.contains(key);
    }

    protected abstract View getFragmentView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

}
