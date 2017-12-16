package com.enfle.android.mediacontent.movie.list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.activity.DaggerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends DaggerActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTableLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new MovieFragmentAdapter(this, getSupportFragmentManager()));
        mTableLayout.setupWithViewPager(mViewPager, true);
        mToolbar.setTitle(R.string.movies);
    }
}
