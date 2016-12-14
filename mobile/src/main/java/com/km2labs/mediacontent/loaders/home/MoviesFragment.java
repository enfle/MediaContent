package com.km2labs.mediacontent.loaders.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.core.fragments.BaseFragment;
import com.km2labs.mediacontent.loaders.movie.list.MovieFragmentAdapter;

import butterknife.BindView;

/**
 * Created by : Subham Tyagi
 * Created on :  25/10/16.
 */

public class MoviesFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTableLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;


    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager.setAdapter(new MovieFragmentAdapter(getContext(), getFragmentManager()));
        mTableLayout.setupWithViewPager(mViewPager, true);
    }
}
