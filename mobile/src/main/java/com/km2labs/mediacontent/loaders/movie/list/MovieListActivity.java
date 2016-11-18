package com.km2labs.mediacontent.loaders.movie.list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.loaders.PresenterActivity;

import butterknife.BindView;

public class MovieListActivity extends PresenterActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTableLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.movie_list_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager.setAdapter(new MovieFragmentAdapter(this, getSupportFragmentManager()));
        mTableLayout.setupWithViewPager(mViewPager, true);
        mToolbar.setTitle(R.string.movies);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }


}
