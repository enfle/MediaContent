package com.km2labs.mediacontent.loaders.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.core.activity.BaseActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(new NowFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.action_now:
                fragment = new NowFragment();
                break;
            case R.id.action_movies:
                fragment = new MoviesFragment();
                break;
            case R.id.action_tv:
                fragment = new TvFragment();
                break;
            case R.id.action_favorite:
                fragment = new FavraouteFragment();
                break;
            default:
        }
        if (fragment != null)
            changeFragment(fragment);
        return false;
    };

    void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, fragment).commit();
    }
}
